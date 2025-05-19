# Exercício 4: Fila de Mensagens

**Cenário:**  
Um sistema de processamento de pedidos precisa garantir que **cada pedido seja processado exatamente uma vez**, mesmo em caso de falhas.

**Objetivos da solução:**
- Receber pedidos de múltiplas origens (Web, Mobile, API)
- Distribuir o processamento entre múltiplos workers
- Garantir o processamento exatamente uma vez
- Implementar retry automático para mensagens com falhas
- Lidar com diferentes cenários de falha

---

## Solução proposta com Fila de Mensagens

### Componentes da arquitetura

1. **Produtores de Mensagem**
   - Enviam pedidos de diferentes fontes (web, mobile, API).
   - Publicam as mensagens em uma fila central.

2. **Fila de Mensagens (Ex: Amazon SQS, RabbitMQ, Azure Queue)**
   - Armazena os pedidos com persistência.
   - Garante visibilidade temporária (mensagens ficam invisíveis para outros workers durante o processamento).

3. **Workers consumidores**
   - Processam os pedidos recebidos da fila.
   - Marcam as mensagens como "em processamento".
   - Removem da fila apenas após confirmação de sucesso.

4. **Retry e Dead Letter Queue (DLQ)**
   - Mensagens que falham são reenviadas à fila para nova tentativa.
   - Após X tentativas falhas, a mensagem é enviada para a **DLQ**.
   - DLQ permite inspeção e tratamento manual de erros persistentes.

---

### Fluxo de Processamento

[Web / Mobile / API]
↓
Envia pedido à fila
↓
[Fila de Mensagens]
↓
Workers recebem e processam mensagens
↓
✓ Se sucesso: remove da fila
✗ Se falha: reencaminha para fila
↪ Após N falhas: envia para DLQ


---

### Tratamento de falhas

| Situação                         | Ação do sistema |
|----------------------------------|-----------------|
| Worker falha durante o processamento | Mensagem volta para a fila após timeout |
| Worker processa, mas não confirma | Mensagem visível novamente na fila |
| Erro de lógica contínuo          | Mensagem enviada à DLQ após X tentativas |
| Worker lento ou sobrecarregado   | Novos workers podem ser instanciados automaticamente (auto scaling) |

---

### Benefícios da solução

- **Tolerância a falhas:** Nenhuma mensagem é perdida, mesmo em caso de falha.
- **Processamento confiável:** Mensagens só são removidas após sucesso confirmado.
- **Escalabilidade horizontal:** Múltiplos workers podem ser executados em paralelo.
- **Resiliência e isolamento:** Mensagens problemáticas vão para a DLQ sem travar o sistema.

---


