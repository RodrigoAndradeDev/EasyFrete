# EASYFRETE — SISTEMA DE GERENCIAMENTO DE ENTREGAS

## 1. Nome e objetivo da aplicação

**EasyFrete — Sistema de Gerenciamento de Entregas**

O EasyFrete é uma aplicação desenvolvida em Java com o objetivo de gerenciar entregas, permitindo o cadastro de clientes e endereços, a escolha de modalidades de entrega, o cálculo de fretes e o acompanhamento dos status das entregas.

A aplicação também demonstra a utilização de seis padrões de projeto, distribuídos entre as categorias criacional, estrutural e comportamental.

## 2. Descrição do problema

Empresas de transporte e logística precisam gerenciar diferentes modalidades de entrega, cada uma com suas próprias regras de cálculo de frete. Além disso, podem utilizar serviços externos para realizar determinados cálculos e precisam manter os clientes informados sobre as mudanças no andamento das entregas.

Sem uma organização adequada, a inclusão de novas modalidades, a integração com serviços externos e o gerenciamento de notificações podem aumentar o acoplamento entre as classes e dificultar a manutenção do sistema.

O EasyFrete propõe uma solução simplificada para esse cenário, utilizando padrões de projeto para organizar as responsabilidades e facilitar a evolução da aplicação.

## 3. Principais funcionalidades

A aplicação disponibiliza as seguintes funcionalidades por meio de um menu interativo no console:

- **Cadastro de entregas:** permite registrar clientes, endereços de origem e destino, peso, distância e modalidade de entrega.
- **Listagem de entregas:** apresenta as entregas cadastradas e suas respectivas informações.
- **Busca por identificador:** permite localizar uma entrega específica por meio de seu UUID.
- **Cálculo de frete:** realiza o cálculo de acordo com a modalidade selecionada, incluindo uma modalidade terceirizada que simula a utilização de um serviço externo.
- **Atualização de status:** permite alterar o status de uma entrega entre aguardando, em trânsito, entregue e cancelada.
- **Gerenciamento de notificações:** permite ativar e desativar observadores responsáveis por exibir notificações no console e registrar alterações no histórico.
- **Consulta de histórico:** apresenta os registros das alterações de status recebidas pelo observador de histórico.

Os dados são armazenados em memória durante a execução da aplicação, utilizando coleções Java. Portanto, não há persistência em banco de dados.

## 4. Identificação dos seis padrões de projeto

Os seis padrões utilizados estão distribuídos em três categorias:

### 4.1 Padrões criacionais

1. **Builder:** utilizado na construção de objetos `Entrega`, permitindo informar seus atributos de maneira progressiva e validar os dados obrigatórios antes da criação.
2. **Factory Method:** utilizado para criar os diferentes objetos responsáveis pelo cálculo de frete, por meio de criadores específicos para cada modalidade.

### 4.2 Padrões estruturais

3. **Adapter:** utilizado para adaptar a interface de um serviço externo de cálculo de frete à interface interna `CalculoFrete`.
4. **Proxy:** utilizado para controlar o acesso às estratégias de cálculo, realizando validações antes de delegar a operação ao objeto responsável pelo cálculo.

### 4.3 Padrões comportamentais

5. **Strategy:** utilizado para encapsular os diferentes algoritmos de cálculo de frete, permitindo utilizar uma implementação diferente conforme a modalidade escolhida.
6. **Observer:** utilizado para notificar componentes interessados quando o status de uma entrega é atualizado, permitindo executar ações independentes, como exibir mensagens e registrar o histórico.

## 5. Onde e por que cada padrão foi implementado

### 5.1 Builder — Padrão criacional

**Classes envolvidas:** `EntregaBuilder` e `Entrega`.

O Builder foi implementado para facilitar a criação de objetos `Entrega`, que possuem diversos atributos necessários para seu funcionamento.

A classe `EntregaBuilder` disponibiliza métodos encadeados para informar os dados do objeto, como cliente, origem, destino, peso, distância e modalidade.

Ao executar o método `build()`, são realizadas validações para verificar se os campos obrigatórios foram preenchidos e se os valores de peso e distância são positivos.

Esse padrão evita a utilização de construtores com muitos parâmetros e torna a criação das entregas mais organizada e legível.

### 5.2 Factory Method — Padrão criacional

**Classes envolvidas:** `CriadorCalculoFrete`, `CriadorFreteEconomico`, `CriadorFreteExpresso`, `CriadorFreteAgendado` e `CriadorFreteTerceirizado`.

O Factory Method foi implementado para delegar a criação dos objetos responsáveis pelo cálculo de frete a classes específicas.

A classe abstrata `CriadorCalculoFrete` define o método `criarCalculoFrete()`, enquanto suas subclasses implementam esse método retornando a estratégia correspondente à modalidade.

Dessa forma, cada criador é responsável pela construção de um objeto específico, reduzindo a dependência direta entre o código que solicita o cálculo e as classes concretas de cálculo.

### 5.3 Adapter — Padrão estrutural

**Classes envolvidas:** `FreteTerceirizadoAdapter`, `ServicoExternoFrete` e `CalculoFrete`.

O Adapter foi implementado para permitir a utilização de um serviço externo de cálculo de frete que possui uma interface diferente daquela adotada internamente pela aplicação.

Internamente, o sistema utiliza a interface `CalculoFrete`, que recebe um objeto `Entrega`. Entretanto, o serviço externo espera receber parâmetros separados, como peso, CEP de origem e CEP de destino.

A classe `FreteTerceirizadoAdapter` realiza essa adaptação, recebendo uma entrega e extraindo os dados necessários para chamar o método `calcularValorFrete()` do serviço externo.

Assim, o restante da aplicação consegue utilizar o serviço terceirizado sem precisar conhecer sua interface específica.

### 5.4 Proxy — Padrão estrutural

**Classes envolvidas:** `CalculoFreteProxy` e `CalculoFrete`.

O Proxy foi implementado para controlar o acesso aos objetos responsáveis pelo cálculo de frete.

A classe `CalculoFreteProxy` implementa a mesma interface `CalculoFrete` e mantém uma referência para a estratégia real de cálculo.

Antes de delegar a operação, verifica se a entrega foi informada corretamente, se o peso é positivo e se os endereços de origem e destino existem.

Caso as validações sejam aprovadas, o Proxy encaminha a solicitação ao objeto responsável pelo cálculo.

Esse padrão permite adicionar uma camada de controle sem modificar as implementações das estratégias de frete.

### 5.5 Strategy — Padrão comportamental

**Classes envolvidas:** `CalculoFrete`, `FreteEconomico`, `FreteExpresso` e `FreteAgendado`.

O Strategy foi implementado para organizar os diferentes algoritmos de cálculo de frete.

A interface `CalculoFrete` define o método `calcular()`, que é implementado pelas classes concretas de cada modalidade.

Cada implementação possui sua própria fórmula:

- **Econômica:** peso multiplicado por 2,0, somado à distância multiplicada por 0,1.
- **Expressa:** peso multiplicado por 3,5, somado à distância multiplicada por 0,20.
- **Agendada:** peso multiplicado por 2,5, somado à distância multiplicada por 0,15.

A modalidade terceirizada também pode ser utilizada por meio do Adapter, que implementa a mesma interface.

Dessa maneira, o sistema consegue utilizar diferentes formas de cálculo sem concentrar todos os algoritmos em uma única classe.

### 5.6 Observer — Padrão comportamental

**Classes envolvidas:** `EntregaObserver`, `EntregaConsoleObserver`, `EntregaHistoricoObserver` e `ServicoEntrega`.

O Observer foi implementado para permitir que diferentes componentes sejam notificados quando o status de uma entrega é alterado.

A interface `EntregaObserver` define o método `atualizar()`, que é implementado pelos observadores concretos.

A classe `ServicoEntrega` mantém uma lista de observadores e disponibiliza métodos para adicioná-los, removê-los e notificá-los.

Atualmente, existem duas implementações:

- **EntregaConsoleObserver:** exibe uma mensagem no console informando que o status da entrega foi atualizado.
- **EntregaHistoricoObserver:** registra o identificador da entrega e seu novo status em uma lista de histórico, permitindo consultar as alterações posteriormente.

Assim, o serviço responsável pelas entregas não precisa conhecer os detalhes das ações executadas por cada observador.

## 6. Aplicação dos princípios GRASP

Os princípios GRASP foram utilizados para distribuir responsabilidades entre as classes, reduzir o acoplamento e melhorar a organização da aplicação.

### 6.1 Information Expert (Especialista na Informação)

As responsabilidades são atribuídas às classes que possuem as informações necessárias para executá-las.

A classe `Entrega`, por exemplo, mantém os dados da entrega, como peso, distância, modalidade e endereços. As estratégias de cálculo utilizam essas informações para determinar o valor do frete.

A classe `EntregaHistoricoObserver` mantém os registros das alterações recebidas, sendo responsável pela consulta ao histórico.

### 6.2 Creator (Criador)

O princípio Creator orienta a atribuição da responsabilidade de criação de objetos às classes que possuem uma relação adequada com eles.

No projeto, a classe `EntregaBuilder` é responsável por construir objetos `Entrega`, enquanto as subclasses de `CriadorCalculoFrete` são responsáveis por criar as respectivas estratégias de cálculo.

### 6.3 Controller (Controlador)

O princípio Controller orienta a utilização de uma classe responsável por receber e coordenar solicitações relacionadas às operações do sistema.

A classe `ServicoEntrega` desempenha esse papel ao centralizar operações como cadastro, busca, listagem e atualização de status das entregas.

A classe `EasyFreteConsole` recebe as entradas do usuário e encaminha as solicitações aos serviços e demais componentes.

### 6.4 Low Coupling (Baixo Acoplamento)

O baixo acoplamento é favorecido pela utilização de interfaces e pela separação das responsabilidades.

As estratégias implementam `CalculoFrete`, enquanto o Proxy e o Adapter também utilizam essa interface. Isso permite que o serviço de cálculo trabalhe com diferentes implementações sem depender diretamente de suas classes concretas.

O Observer também reduz o acoplamento, pois `ServicoEntrega` notifica os observadores por meio da interface `EntregaObserver`.

### 6.5 High Cohesion (Alta Coesão)

As classes possuem responsabilidades específicas e relacionadas aos seus objetivos.

Por exemplo:

- `EntregaBuilder`: construção e validação inicial de entregas.
- `ServicoEntrega`: gerenciamento das entregas.
- `ServicoCalculoFrete`: execução e armazenamento do resultado do cálculo.
- `CalculoFreteProxy`: validação e controle de acesso ao cálculo.
- `FreteTerceirizadoAdapter`: adaptação da interface externa.
- `EntregaConsoleObserver`: exibição de notificações.

Essa organização facilita a compreensão, manutenção e evolução do sistema.

### 6.6 Polymorphism (Polimorfismo)

O polimorfismo é utilizado por meio de interfaces e herança.

As diferentes modalidades de cálculo implementam a interface `CalculoFrete`, permitindo que o sistema execute diferentes algoritmos por meio de uma referência comum.

Da mesma forma, os observadores implementam `EntregaObserver`, possibilitando que o serviço notifique diferentes componentes sem precisar conhecer suas implementações específicas.

## 7. Principais conceitos de Programação Orientada a Objetos

A implementação utiliza os principais conceitos de Programação Orientada a Objetos:

### 7.1 Encapsulamento

Os atributos das classes são declarados como privados (`private`), permitindo controlar o acesso e a modificação dos dados por meio de métodos públicos, como getters e setters.

### 7.2 Abstração

As interfaces `CalculoFrete` e `EntregaObserver`, assim como a classe abstrata `CriadorCalculoFrete`, definem contratos e comportamentos sem exigir que os componentes dependentes conheçam os detalhes das implementações concretas.

### 7.3 Herança

As classes `CriadorFreteEconomico`, `CriadorFreteExpresso`, `CriadorFreteAgendado` e `CriadorFreteTerceirizado` herdam de `CriadorCalculoFrete`.

### 7.4 Polimorfismo

O sistema utiliza referências de interfaces e classes abstratas para trabalhar com diferentes implementações de maneira uniforme, como ocorre nas estratégias de cálculo e nos observadores.

### 7.5 Composição e associação

As classes estabelecem relacionamentos para representar as dependências e estruturas do sistema.

Por exemplo, `Entrega` possui referências para `Cliente` e `Endereco`, enquanto `ServicoEntrega` mantém coleções de entregas e observadores.

## 8. Instruções para executar a aplicação no Eclipse

Para executar o EasyFrete, é necessário possuir o Java Development Kit (JDK) instalado e o ambiente de desenvolvimento Eclipse configurado.

**Passo 1 — Abrir o projeto**

Abra o Eclipse e importe o projeto Java utilizando a opção:

`File → Import → General → Existing Projects into Workspace`

Selecione o diretório do projeto EasyFrete.

**Passo 2 — Verificar a estrutura**

Confirme se os pacotes estão organizados dentro de `src`, incluindo:

- `com.easyfrete.adapter`
- `com.easyfrete.builder`
- `com.easyfrete.console`
- `com.easyfrete.enums`
- `com.easyfrete.factory`
- `com.easyfrete.model`
- `com.easyfrete.observer`
- `com.easyfrete.proxy`
- `com.easyfrete.service`
- `com.easyfrete.strategy`

Verifique também se a classe `Main` está no pacote `com.easyfrete`.

**Passo 3 — Executar a aplicação**

Localize a classe `Main.java`, clique com o botão direito sobre ela e selecione:

`Run As → Java Application`

**Passo 4 — Utilizar o sistema**

Após a execução, o menu principal será apresentado no console do Eclipse.

Digite o número correspondente à funcionalidade desejada e pressione Enter para navegar pelas opções.

A aplicação continuará em execução até que a opção `0 - Sair` seja selecionada.

**Observação:** o EasyFrete utiliza armazenamento em memória. Dessa forma, os dados cadastrados e o histórico são mantidos somente durante a execução atual do programa.
