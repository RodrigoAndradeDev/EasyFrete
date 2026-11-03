# EasyFrete — Plano de Implementação

## 1. Estratégia de desenvolvimento

O EasyFrete será desenvolvido de maneira incremental.

Inicialmente, será construída uma versão funcional e simples da aplicação, sem utilização de Design Patterns.

Durante a evolução do projeto, serão identificados problemas de organização, acoplamento e extensibilidade que justificarão a introdução dos padrões de projeto selecionados.

Cada padrão será implementado individualmente, seguido de testes e integração com os componentes existentes.

## 2. Etapas de desenvolvimento

### Etapa 1 — Estrutura inicial e modelagem

Objetivo: estabelecer a estrutura básica da aplicação.

Implementações:
- Criar o projeto Java no Eclipse.
- Criar os pacotes iniciais.
- Implementar Cliente.
- Implementar Endereco.
- Implementar Entrega.
- Implementar StatusEntrega.
- Implementar TipoEntrega.
- Implementar ServicoEntrega.
- Criar a classe Main.

Resultado esperado:

Uma aplicação executável que permita cadastrar e listar entregas em memória.

### Etapa 2 — Primeiro sistema de cálculo de frete

Objetivo: implementar o cálculo inicial das modalidades de entrega.

Implementações:
- Criar a classe CalculadoraFrete.
- Implementar os cálculos de frete econômico e expresso.
- Utilizar estruturas condicionais para selecionar o cálculo.
- Integrar o cálculo ao cadastro de entregas.

Problema identificado:

O crescimento da quantidade de modalidades torna a classe responsável pelo cálculo cada vez mais complexa.

Necessidade identificada:

Separar os algoritmos de cálculo.

Padrão introduzido: Strategy.

### Etapa 3 — Criação das estratégias de cálculo

Objetivo: separar os algoritmos de cálculo em implementações independentes.

Implementações:
- Criar a interface CalculoFrete.
- Criar FreteEconomico.
- Criar FreteExpresso.
- Criar FreteAgendado.
- Adaptar o sistema para trabalhar com a interface.

Resultado esperado:

O sistema deverá executar diferentes algoritmos de cálculo sem depender de estruturas condicionais concentradas em uma única classe.

### Etapa 4 — Centralização da criação de estratégias

Objetivo: organizar a instanciação das diferentes modalidades de cálculo.

Implementações:
- Criar CriadorCalculoFrete.
- Criar CriadorFreteEconomico.
- Criar CriadorFreteExpresso.
- Criar CriadorFreteTerceirizado.
- Integrar os criadores ao fluxo de seleção de modalidades.

Problema identificado:

A criação direta das estratégias começa a espalhar a lógica de instanciação pela aplicação.

Necessidade identificada:

Delegar a criação dos objetos a uma estrutura especializada.

Padrão introduzido: Factory Method.

### Etapa 5 — Construção de entregas

Objetivo: simplificar a criação de objetos Entrega.

Implementações:
- Identificar atributos obrigatórios e opcionais.
- Criar EntregaBuilder.
- Implementar métodos para configurar os atributos.
- Implementar o método build().
- Substituir a utilização de construtores extensos pela construção progressiva.

Problema identificado:

O crescimento da quantidade de atributos torna a instanciação de Entrega menos legível e mais difícil de manter.

Necessidade identificada:

Permitir a construção progressiva de objetos.

Padrão introduzido: Builder.

### Etapa 6 — Integração com serviço externo

Objetivo: simular a integração com um serviço externo de cálculo de frete.

Implementações:
- Criar ServicoExternoFrete.
- Definir uma interface diferente da utilizada internamente.
- Criar FreteTerceirizadoAdapter.
- Implementar a adaptação entre as interfaces.
- Integrar o Adapter ao sistema de cálculo.

Problema identificado:

O serviço externo possui uma interface incompatível com a abstração utilizada pelo EasyFrete.

Necessidade identificada:

Permitir a comunicação entre componentes com interfaces diferentes.

Padrão introduzido: Adapter.

### Etapa 7 — Controle de acesso ao serviço

Objetivo: adicionar uma camada de controle antes da execução dos cálculos.

Implementações:
- Criar CalculoFreteProxy.
- Implementar validações de dados.
- Impedir o encaminhamento de solicitações inválidas.
- Delegar as operações válidas ao objeto real.
- Integrar o Proxy ao fluxo de cálculo.

Problema identificado:

O acesso direto ao serviço dificulta a centralização das validações e do controle das chamadas.

Necessidade identificada:

Controlar o acesso ao objeto responsável pelo cálculo.

Padrão introduzido: Proxy.

### Etapa 8 — Acompanhamento e notificações

Objetivo: implementar o acompanhamento das entregas.

Implementações:
- Criar EntregaObserver.
- Criar EntregaConsoleObserver.
- Implementar o registro de observadores.
- Implementar o mecanismo de notificação.
- Integrar as notificações à atualização de status.

Problema identificado:

A necessidade de notificar diferentes componentes após alterações de status poderia criar dependências diretas entre as classes.

Necessidade identificada:

Estabelecer um mecanismo de notificação desacoplado.

Padrão introduzido: Observer.

### Etapa 9 — Integração e validação final

Objetivo: garantir que todos os componentes funcionem corretamente em conjunto.

Implementações:
- Revisar a organização dos pacotes.
- Verificar os relacionamentos entre classes.
- Validar os seis padrões.
- Testar o fluxo completo de cadastro e cálculo.
- Testar a integração com o serviço externo simulado.
- Testar as notificações.
- Revisar o tratamento de erros.
- Revisar o diagrama UML.

Resultado esperado:

Uma aplicação funcional, organizada e capaz de demonstrar a utilização integrada dos seis padrões de projeto.

## 3. Cenários de teste e demonstração

A aplicação deverá permitir demonstrar os seguintes cenários:

### Cenário 1 — Cadastro de entrega
- Informar os dados do cliente.
- Informar origem e destino.
- Informar peso e distância.
- Configurar atributos opcionais.
- Construir e cadastrar a entrega.

### Cenário 2 — Cálculo de frete
- Selecionar uma modalidade.
- Obter a estratégia correspondente.
- Executar o cálculo.
- Apresentar o valor final.

### Cenário 3 — Serviço externo
- Selecionar a modalidade terceirizada.
- Executar a adaptação da interface.
- Encaminhar a solicitação ao serviço simulado.
- Exibir o resultado.

### Cenário 4 — Validação de acesso
- Tentar calcular uma entrega com peso inválido.
- Verificar o bloqueio da operação.
- Tentar calcular uma entrega válida.
- Verificar o encaminhamento ao serviço real.

### Cenário 5 — Alteração de status
- Consultar uma entrega.
- Alterar seu status.
- Verificar a atualização.
- Demonstrar o recebimento das notificações pelos observadores.

## 4. Critérios de conclusão

O projeto será considerado concluído quando:

- [ ] Todas as funcionalidades principais estiverem operacionais.
- [ ] Os seis padrões estiverem efetivamente implementados.
- [ ] Os padrões estiverem integrados ao funcionamento da aplicação.
- [ ] Os princípios GRASP estiverem representados na arquitetura.
- [ ] O código estiver organizado em pacotes.
- [ ] O diagrama UML estiver atualizado.
- [ ] A documentação técnica estiver concluída.
- [ ] A aplicação puder ser executada no Eclipse.