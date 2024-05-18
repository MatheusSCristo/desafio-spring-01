# Desafio Spring Boot
Bem-vindo à minha resolução do Desafio Spring Boot NExT 2023.1, uma aplicação Spring Boot projetada para gerenciar clientes, casas, veículos e apólices de seguro.

## Visão Geral

O projeto fornece uma API RESTful para realizar operações CRUD (Create, Read, Update, Delete) em várias entidades, juntamente com lógica de negócios para gerar apólices de seguro com base nas informações do cliente e fatores de risco.

## Estrutura do Projeto

O projeto é estruturado em diversos componentes:

### Entidades

- **Cliente**: Representa um cliente com atributos como nome, idade, renda, estado civil, etc. Os clientes podem possuir vários veículos e casas.
- **Casa**: Representa uma casa com atributos como estado de propriedade, código postal, localização, etc. As casas estão associadas a clientes.
- **Veículo**: Representa um veículo com atributos como marca, modelo, ano, etc. Os veículos estão associados a clientes.
- **Seguro**: Representa uma apólice de seguro com atributos como tipo, risco, análise, etc. Os seguros estão associados a clientes e podem ser de tipos diferentes como vida, invalidez, residência ou veículo.

### Serviços

- **Serviço de Cliente**: Fornece operações para gerenciar clientes, incluindo métodos para criar, ler, atualizar e excluir clientes.
- **Serviço de Casa**: Fornece operações para gerenciar casas, incluindo métodos para criar, ler, atualizar e excluir casas.
- **Serviço de Veículo**: Fornece operações para gerenciar veículos, incluindo métodos para criar, atualizar e excluir veículos, bem como associar veículos a clientes.
- **Serviço de Seguro**: Fornece métodos para calcular apólices de seguro com base nas informações do cliente e fatores de risco, incluindo apólices de seguro de vida, invalidez, residência e veículo.

### Tratamento de Exceções

- **RestHandlerExceptions**: Trata exceções lançadas pelos controladores, fornecendo respostas personalizadas para vários cenários de erro.

### Princípio DRY (Don't Repeat Yourself)

- O projeto ChallengeSpring1 adota o princípio DRY , buscando reduzir a duplicação de código e promover a reutilização de lógica através de componentes modulares e abstração de funcionalidades comuns.

## Desafio Original
- **Repositório do Desafio**: https://github.com/NExT-2023-1/desafio-spring
