# Sistema de Gerenciamento de Funcionários

Sistema desenvolvido em Java com o objetivo de simular o gerenciamento de funcionários de uma empresa. O projeto permite cadastrar, consultar, autenticar e administrar diferentes tipos de funcionários, aplicando conceitos fundamentais de Programação Orientada a Objetos.

## Sobre o Projeto

O sistema foi criado para representar um cenário real de administração de funcionários dentro de uma empresa. Cada funcionário possui informações como CPF, salário e data de admissão, enquanto cargos específicos possuem características próprias.

Durante o desenvolvimento foram implementadas regras de negócio para validação de dados, autenticação de usuários e controle de funcionários cadastrados.

## Funcionalidades

- Cadastro de funcionários
- Remoção de funcionários
- Busca de funcionários por CPF
- Listagem de funcionários cadastrados
- Exibição da folha salarial da empresa
- Autenticação de gerente
- Consulta de funcionários por período de admissão
- Tratamento de erros através de exceções personalizadas

## Modelo de Funcionários

O sistema trabalha com diferentes tipos de funcionários:

### Gerente

Possui acesso ao sistema por meio de autenticação utilizando senha.

### Secretária

Funcionário que possui adicional de salário conforme as regras definidas na aplicação.

### Vendedor

Funcionário responsável por vendas e que pode possuir regras específicas relacionadas à sua remuneração.

## Estrutura da Aplicação

A classe `Empresa` é responsável por gerenciar todos os funcionários cadastrados.

As operações principais incluem:

- Adicionar funcionários
- Remover funcionários
- Buscar funcionários
- Calcular folha salarial
- Listar informações cadastradas

## Validações Implementadas

O sistema realiza validações para garantir a consistência dos dados:

- Verificação de CPF duplicado
- Validação de CPF informado
- Validação de datas
- Verificação de funcionários inexistentes

## Conceitos de Programação Aplicados

- Programação Orientada a Objetos (POO)
- Encapsulamento
- Herança
- Polimorfismo
- Interfaces
- Sobrescrita de métodos
- Tratamento de exceções
- Collections (ArrayList)
- Organização em camadas de responsabilidade

