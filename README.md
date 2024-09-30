# `Projeto Final - Agrix - Fase A`

Boas-vindas ao repositório do projeto `Projeto Final - Agrix - Fase A`

<details>
  <summary>👨‍💻 O que foi desenvolvido</summary><br />

Maria e João são pessoas empreendedoras que estão muito preocupadas com os impactos ambientais e
sociais dos nossos processos agrícolas. Por isso, decidiram criar a AgroTech, uma empresa
especializada em tecnologias para melhorar a eficiência no cultivo de plantações. Isso visa reduzir
o desperdício de recursos em geral e de alimentos em específico, fazendo um uso mais responsável da
terra disponível para plantio.

O primeiro produto dessa empresa será o Agrix, um sistema que permitirá a gestão e o monitoramento
das fazendas participantes. Esse produto será desenvolvido em fases.
</details>

<details>

   <summary><strong>‼ Antes de começar </strong></summary>

1. Clone o repositório

- Copie o endereço SSH do repositório e use-o para cloná-lo na sua máquina:
    - Por exemplo: `git clone <Link SSH do repositório>`
- Entre na pasta do repositório que você acabou de clonar:
    - `cd <nome do repositório>`

2. Instale as dependências

- Para instalar todas as denpendências utilize o comando:
    - `mvn dependency:resolve`
- Para fazer todo o processo de instalação de dependências, compilação, testes, empacotamento e instalação no repositório local, utilize o comando:
    - `mvn install`

</details>

<details>
<summary><strong>🛠 Testes</strong></summary>

Para executar todos os testes basta rodar o comando:

```bash
mvn test
```

Para executar apenas uma classe de testes:

```bash
mvn test -Dtest="TestClassName"
```

</details>

<summary>🗄️ Descrição do banco de dados</summary><br>

O banco:

![Modelo de tabelas](images/agrix-tabelas-fase-b.png)

Nesse modelos, temos as seguintes tabelas:
- `farm`: representa uma fazenda
- `crop`: representa uma plantação, e está em relacionamento `n:1` ("muitos para um") com a tabela `farm`
- `fertilizer`: esta nova tabela representa um fertilizante, e está em um relacionamento `n:n` ("muitos para muitos") com a tabela `crop`. Esse relacionamento é realizado através da tabela `crop_fertilizer`.

</details>
