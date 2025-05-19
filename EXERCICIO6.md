# Exercício 6: Serverless e FaaS

**Proposta:** Projetar uma aplicação Serverless para processamento automático de imagens.

**Requisitos:**
- Quando uma imagem for enviada para um bucket de armazenamento, ela deve ser redimensionada em múltiplos tamanhos.
- Os metadados da imagem devem ser extraídos e armazenados em um banco de dados.
- Para imagens grandes, deve ser gerada uma versão em miniatura (thumbnail).
- A solução deve escalar automaticamente com o aumento de carga.

---

## Solução com serviços AWS

### Componentes utilizados

1. **Amazon S3**
   - Armazena as imagens originais enviadas pelos usuários.
   - Um evento `PUT` em um bucket (ex: `input-images/`) aciona automaticamente uma função Lambda.

2. **AWS Lambda**
   - Função acionada quando uma imagem é enviada ao S3.
   - Responsável por:
     - Redimensionar a imagem em múltiplos tamanhos (ex: 128x128, 512x512, 1024x1024).
     - Extrair metadados da imagem (dimensões, tipo, tamanho, data).
     - Gerar uma **thumbnail** (ex: 64x64) se a imagem for grande.
     - Armazenar as versões processadas em buckets específicos (ex: `resized-images/`, `thumbnails/`).

3. **Amazon DynamoDB**
   - Armazena os metadados das imagens processadas.
   - Estrutura do registro:
     - Nome do arquivo
     - Dimensões geradas
     - Data de envio
     - Usuário
     - Localização no S3

4. **AWS CloudWatch**
   - Monitora a execução das funções Lambda.
   - Gera métricas, logs e alertas em caso de falhas.

---

### Fluxo da aplicação

Usuário envia imagem
↓
S3 (input-images bucket)
↓ (evento PUT)
Lambda é acionada
↓
Processa imagem → Redimensiona → Gera thumbnail
↓
Salva imagens redimensionadas no S3
↓
Salva metadados no DynamoDB


---

### Escalabilidade Automática

- **AWS Lambda** escala automaticamente com base na demanda — múltiplas imagens enviadas simultaneamente são processadas por instâncias paralelas.
- **Amazon S3** permite uploads em larga escala sem configuração adicional.
- **DynamoDB** com modo on-demand ajusta automaticamente a capacidade de leitura e escrita conforme o volume de dados.

---

### Vantagens da solução

- **Arquitetura totalmente Serverless**: sem necessidade de servidores provisionados.
- **Alta escalabilidade**: componentes ajustam recursos automaticamente.
- **Custo eficiente**: pagamento por uso (execução, armazenamento, requisições).
- **Extensível**: fácil de adicionar novos formatos ou lógicas de processamento.

---


