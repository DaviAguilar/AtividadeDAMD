# Exercício 3: Comunicação Indireta e Pub/Sub

Projete um sistema de notificações para uma plataforma de e-commerce usando o paradigma Pub/Sub. O sistema deve:

- Permitir que usuários se inscrevam para receber atualizações sobre produtos específicos.
- Notificar sobre mudanças de preço, disponibilidade de estoque e promoções.
- Suportar diferentes canais de entrega (email, SMS, notificações push).
- Identificar publicadores, assinantes, tópicos e filtros.

---

## Arquitetura proposta com Pub/Sub

### Componentes principais

- **Publicadores (Publishers):** Serviços internos que geram eventos quando ocorre uma mudança (preço, estoque, promoção).
- **Tópicos (Topics):** Canais de mensagens categorizados por tipo de evento e produto (ex: `produto.123.preco`).
- **Broker Pub/Sub:** Sistema intermediário que distribui os eventos para os assinantes (ex: Apache Kafka, AWS SNS, Google Pub/Sub).
- **Assinantes (Subscribers):** Serviços que processam e entregam as notificações (EmailService, SMSService, PushService).
- **Filtros personalizados:** Cada usuário escolhe os tópicos e canais pelos quais quer ser notificado.

---

## Exemplos de Tópicos

- `produto.123.preco`
- `produto.123.estoque`
- `produto.123.promocao`

---

## Exemplo de fluxo real

O usuário A se inscreve no tópico produto.123.preco via e-mail.

O preço do produto 123 muda.

O serviço de produtos publica um evento no tópico.

O broker Pub/Sub recebe e repassa o evento para o EmailService.

O EmailService envia a notificação para o usuário A.

---

## Características do sistema

Desacoplamento: Publicadores e assinantes não precisam se conhecer.

Escalabilidade: Alta capacidade de envio simultâneo de notificações.

Multicanal: Entrega via e-mail, SMS, push e outros com facilidade de expansão.

Personalização: Usuários escolhem o que seguir e como querem ser notificados.

Tolerância a falhas: Com retries e filas persistentes, garante entrega confiável.

---

## Tecnologias recomendadas
Broker Pub/Sub:

AWS SNS + SQS

Apache Kafka

Google Cloud Pub/Sub

Envio de e-mail:

Amazon SES

SendGrid

Envio de SMS:

Twilio

AWS Pinpoint

Push Notifications:

Firebase Cloud Messaging (FCM)

---

## Fluxo do Sistema

```text
              ┌──────────────────────┐
              │  Serviço de Produtos │
              └─────────┬────────────┘
                        │ (evento)
                        ▼
              ┌──────────────────────┐
              │     Broker Pub/Sub   │
              │ (ex: SNS, Kafka...)  │
              └─────────┬────────────┘
                        │
        ┌───────────────┼───────────────────────┐
        ▼               ▼                       ▼
┌─────────────┐ ┌────────────────┐     ┌──────────────────┐
│ EmailService│ │  SMSService    │     │ PushNotification │
└────┬────────┘ └────────┬───────┘     └────────┬─────────┘
     │ Subscrição        │                     │
     ▼                   ▼                     ▼
Usuário A (email)  Usuário B (SMS)     Usuário C (Push)}





