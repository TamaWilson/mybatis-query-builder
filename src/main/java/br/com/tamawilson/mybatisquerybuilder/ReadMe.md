## MyBatisQueryBuilder 

**Mapeamento das Colunas**

Para mapear as colunas é necessário uso da annotation *@MyBatisColumn*
Essa anotaçao possui 4 atributos:

* **name**: Nome da coluna que representa o atributo do objeto no banco
* **nestedClass**: Quando utilizar composição de classes, que signifiquem um relacionamento no banco, essa propriedade 
  mapeia o tipo da classe do objeto
* **rootAs**: Quando utilizar composição de classes, opcionalmente podemos mapear o campo para uma coluna representando uma 
  foreign key do banco e informar o tipo primitivo desse dado.
  
---
**Operações disponíveis**

Númericas:

| Operadores    |  Operacao     |
|---------------|---------------|
|     :         |   igual       |
|     !:        |   diferente   |
|     >         |   maior que   | 
|     <         |   menor que   |
| ::(1,2..99)   |       in      |
| !::(1,2..99)  |    not in     |

Strings:

| Operadores    |  Operacao     |
|---------------|---------------|
|     :         |   igual       |
|     :*        |   contém      |
|     >*        |   começa com  | 
|     <         |   termina com |

conectivos:

| Operadores   |  Operacao     |
|--------------|---------------|
|  &#124;and   |   e           |
|  &#124;or    |  ou           |


----
**Exemplos de mapeamento**

```java
public class Foo {

    @MyBatisColumn(name = "AAA00_id")
    private Long id;

    @MyBatisColumn(name = "AAA00_status_atual")
    private Integer statusAtual;

    @MyBatisColumn(name = "AAA00_qtd")
    private Integer quantidade;

    @MyBatisColumn(name = "AAA00_descricao")
    private String descricao;

    @MyBatisColumn(name= "AAA00_id_bar", nestedClass = Bar.class, rootAs = Long.class)
    private Bar bar;

}
```
```java
public class Bar {

    @MyBatisColumn(name = "AAA01_id")
    private Long id;

    @MyBatisColumn(name = "AAA01_observacao")
    private String observacao;
}
```

Na classe Foo mapeamos todos os atributos básico com seus devidos nomes de coluna. Para a classe aninhada *Bar* definimos
que no banco ela é representada pela coluna `CDF00_id_bar` do tipo `Integer.class` e que a classe filha desse atributo é
`Bar.class`, esse mapaemento permite percorrer a hierarquia das classes ao mesmo tempo que selecionar o atributo Bar pelo 
seu ID na classe pai.

---
**Exemplos**

**Sintaxe básica** 

    <atributoDoObjeto><operador><valor>[|conectivo]

Todos os exemplos utilizam o mapeamento das classes Foo e Bar descritas acima.

* Busca por um atributo simples igual a um valor X:

    `/foo?search=statusAtual:999`


* Busca por uma lista de valores:

    `/foo?search=statusAtual::(1,20,53,45,999)`


* Busca por duas condições:

  `/foo?search=statusAtual:1,descricao:*Teste`


* Busca por duas condições informando como deve ser feita a conjunção/disjunção:

  `/foo?search=statusAtual:1|and,descricao:*Teste|or,quantidade>10`
    *Obs.:* O conectivo deve ser informado após o campo de valor e deve ser separado por `|`


* Busca por atributos de uma classe filha:

    `/foo?search=bar.observacao.id:2`

    *Obs.:* As classes são percorridas colocando `.` para descer na hieraquia. No exemplo acima o atributo `bar` é a raiz e
utilizando `.` acessamos `id` dentro dele.


* Buscar por um maepamento de composição/relacionamento:

    `/foo?search=bar:1`

    *Obs.:* No exemplo acima a query é gerada para a coluna `CDF00_id_bar` da classe `Foo`
