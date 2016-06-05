# Instruções

No Curso 1, nós projetamos um conjunto de classes, usando a Modelagem CRC, para o SAB – Sistema de Automação de Biblioteca. A maioria dos métodos implementados precisa de refatoração, que não foi feita para podermos usar algum deles como exercício de revisão por pares.

Assim solicitamos que examine o método abaixo registraUsuario(String) e faça o seguinte:

a) Identifique uma lista de maus cheiros que você encontra no código, relacionando cada um deles com o correspondente tipo de mau cheiro exercitado nesta parte do curso: [mau cheiro no código (trecho do código)/tipo de mau cheiro (de acordo com Fowler, pode estar em português)].

b) Realize o Ciclo de Refatoração apresentado, eliminando cada um dos maus cheiros encontrado no código do método, considerando apenas os tipos de mau cheiro exercitados nesta Semana 2 do curso.

c) Entregue um documento em que você apresenta o seguinte:

A) Código Antes do método abaixo registraUsuario(String), antes de iniciar o Ciclo de Refatoração.

B) Imagem: Imagem da execução bem-sucedida (verde) no Eclipse ou outro ambiente Java, comprovando que código atual do SAB, incluindo o método registraUsuario(String) está funcionando direito (pelo menos de acordo com a bateria de testes atual).

C) Ciclo de Refatoração até a Lista de Maus Cheiros ficar vazia, apresentando 5 coisas para cada refatoração realizada no Ciclo de Refatoração:

- Antes: O código Antes da refatoração, com o trecho a ser refatorado com as letras coloridas ou com fundo amarelo
- Tipo Mau Cheiro/Técnica de Refatoração: Indique o tipo do mau cheiro identificado no código em 1) e a técnica de refatoração empregada, ambos de acordo com Fowler e podendo estar em português!
- Depois: O código Depois da refatoração, com o trecho refatorado com as letras coloridas ou com fundo verde
- Imagem: Imagem da execução bem-sucedida (verde) no Eclipse ou outro ambiente Java, comprovando que a refatoração foi feita a contento!
- Lista de maus cheiros: Atualize a lista, eliminando o mau cheiro que deu origem à refatoração deste ciclo

D) Código Depois final do método abaixo registraUsuario(String), sem letras ou fundo coloridos!

Procure apresentar o código sempre bem apresentado, de acordo com boas práticas de apresentação/formatação de código Java. Pode usar, por exemplo, o Source/Format do Eclipse ou equivalente do seu ambiente Java.

A bateria de testes atual não pode ser modificada de forma alguma durante o Ciclo de Refatoração!

# Critérios:

Você será avaliado com base no seguinte:

Cumprimento dos requisitos pedidos no enunciado
Organização do documento de apresentação
Identificação precisa dos maus cheiros, tipos de maus cheiros e técnicas de refatoração correspondentes apresentados na Semana 2
Não indicação de maus cheiros, tipos de maus cheiros e técnicas de refatoração não apresentados na Semana 2
Utilização do Ciclo de Refatoração como visto no curso

# Código de proução e de tested do SAB
Carregue o código de produção e de Testes do SAB em seu ambiente Java. Primeiramente, verifique se o código de produção funciona de acordo com a bateria de testes que o acompanha. Ao final da atividade, verifique novamente se o código de produção funciona de acordo com a mesma bateria de testes que o acompanha. Essa bateria de testes deve ser usada sem modificação em todos os ciclos de refatoração empregados na atividade.

Na seção de Material Complementar desta Semana 2 você encontrará tanto o código de produção como o código de testes que desenvolvemos para este Curso 2.

Sugerimos fortemente que use esse material para treinar identificar mau cheiro e realizar refatoração apresentada ou não neste curso. Refatore-o completamente!
