# Exercício: Modelos de Serviços em Cloud

Para cada um dos seguintes casos de uso, identifique o modelo de serviço em nuvem mais adequado (IaaS, PaaS, SaaS ou FaaS) e justifique sua escolha.

---

### 1. Uma aplicação legada escrita em uma tecnologia antiga que precisa de bibliotecas específicas

- **Modelo ideal:** `IaaS (Infrastructure as a Service)`
- **Justificativa:** Aplicações legadas geralmente exigem configurações específicas de sistema operacional e bibliotecas, o que demanda controle total sobre o ambiente. O IaaS permite configurar máquinas virtuais sob medida para manter a compatibilidade com a aplicação antiga.

---

### 2. Um site de e-commerce que precisa escalar durante períodos de alta demanda

- **Modelo ideal:** `PaaS (Platform as a Service)`
- **Justificativa:** O PaaS fornece uma plataforma escalável automaticamente, ideal para aplicações web com variação de tráfego. O provedor gerencia infraestrutura, balanceamento de carga e escalabilidade, permitindo que o foco fique na lógica de negócio.

---

### 3. Um sistema de processamento de imagens que recebe arquivos esporadicamente

- **Modelo ideal:** `FaaS (Function as a Service)`
- **Justificativa:** FaaS é ideal para cargas de trabalho event-driven. Cada imagem pode acionar uma função que processa o arquivo sob demanda, sem a necessidade de manter servidores em execução constante, reduzindo custos e complexidade.

---

### 4. Um sistema de gestão de RH para uma empresa média

- **Modelo ideal:** `SaaS (Software as a Service)`
- **Justificativa:** O SaaS oferece uma solução completa, pronta para uso, com atualizações, suporte e escalabilidade incluídos. É ideal para empresas que querem utilizar o sistema sem se preocupar com desenvolvimento ou manutenção.

---

### 5. Um aplicativo móvel com backend para sincronização de dados

- **Modelo ideal:** `BaaS/FaaS ou PaaS`
- **Justificativa:** 
  - Se o backend é simples (autenticação, banco de dados, notificações), o **BaaS/FaaS** (como Firebase ou AWS Lambda) é ideal pela rapidez de integração.
  - Se o backend exige lógica complexa, APIs personalizadas e banco de dados gerenciado, o **PaaS** é mais adequado, oferecendo escalabilidade e gerenciamento facilitado.

---
