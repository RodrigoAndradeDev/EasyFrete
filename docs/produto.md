# EasyFrete

## 1. Visão geral

O EasyFrete é uma aplicação Java de gerenciamento de entregas desenvolvida para uma pequena transportadora.

O sistema permite cadastrar solicitações de entrega, calcular valores de frete conforme diferentes modalidades de transporte e acompanhar o andamento das entregas por meio de alterações de status.

A aplicação será executada via console e utilizará armazenamento em memória, sem necessidade de banco de dados ou serviços externos reais.

O principal objetivo do projeto é demonstrar a aplicação prática dos conceitos de Programação Orientada a Objetos, princípios GRASP e padrões de projeto (Design Patterns).

## 2. Problema

Uma pequena transportadora precisa organizar suas solicitações de entrega e realizar cálculos de frete considerando diferentes modalidades de transporte.

Inicialmente, a empresa trabalha com modalidades econômicas e expressas, mas deseja permitir a inclusão de novas modalidades sem comprometer o funcionamento das existentes.

Além disso, pretende utilizar um serviço externo de cálculo de frete, que possui uma interface incompatível com a utilizada internamente pelo sistema.

A empresa também precisa controlar o acesso a esse serviço, construir solicitações de entrega com diferentes configurações e notificar os clientes quando houver alterações no andamento de suas entregas.

## 3. Objetivos

- Permitir o cadastro e gerenciamento de entregas.
- Disponibilizar diferentes modalidades de transporte.
- Calcular automaticamente os valores de frete.
- Permitir a construção de entregas com atributos opcionais.
- Simular a integração com um serviço externo de cálculo.
- Controlar o acesso ao serviço de cálculo.
- Acompanhar as alterações de status das entregas.
- Demonstrar a utilização de seis padrões de projeto.

## 4. Funcionalidades principais

### 4.1. Cadastro de entregas

Permitir a criação de solicitações contendo:

- Endereço de origem.
- Endereço de destino.
- Peso da encomenda.
- Distância estimada.
- Modalidade de entrega.
- Dimensões opcionais.
- Observações opcionais.
- Contratação opcional de seguro.

### 4.2. Cálculo de frete

Permitir calcular o valor de uma entrega conforme a modalidade selecionada.

Modalidades iniciais:

- Econômica.
- Expressa.
- Agendada (possível expansão).

### 4.3. Gerenciamento de entregas

Permitir:

- Cadastrar entregas.
- Listar entregas cadastradas.
- Consultar informações de uma entrega.
- Atualizar o status de uma entrega.

### 4.4. Acompanhamento de entregas

Cada entrega poderá possuir os seguintes estados:

- Aguardando.
- Em trânsito.
- Entregue.
- Cancelada.

As alterações de status deverão gerar notificações para os componentes interessados.

### 4.5. Integração com serviço externo

Simular a utilização de um serviço externo responsável pelo cálculo de frete.

O sistema deverá adaptar a interface desse serviço para permitir sua utilização interna.

### 4.6. Validação de solicitações

Antes de encaminhar uma solicitação ao serviço de cálculo, o sistema deverá verificar se os dados necessários são válidos.

### 4.7. Persistência

As informações serão armazenadas temporariamente em memória durante a execução da aplicação.

Não haverá integração com banco de dados nesta versão.

## 5. Restrições do projeto

- Linguagem: Java.
- Ambiente: Eclipse IDE.
- Interface: Console.
- Armazenamento: Memória (Collections).
- Sem banco de dados.
- Sem API REST.
- Sem interface gráfica.
- Sem integração com serviços externos reais.

## 6. Padrões de projeto previstos

### Criacionais
- Factory Method.
- Builder.

### Estruturais
- Adapter.
- Proxy.

### Comportamentais
- Strategy.
- Observer.

## 7. Escopo

O EasyFrete será desenvolvido como uma aplicação acadêmica de pequeno porte.

O foco estará na organização do código, na correta distribuição de responsabilidades e na utilização justificada dos padrões de projeto, evitando funcionalidades que não contribuam diretamente para os objetivos da avaliação.