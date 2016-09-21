insert into usuario (login, email, nome, senha, pontos)
    values ('mario', 'mario@gmail.com', 'It is me, Mario', 'mario', 13)
;

insert into usuario (login, email, nome, senha, pontos)
    values ('luigi', 'luigi@yahoo.com', 'Luigi', 'marioverde', 10)
;

insert into usuario (login, email, nome, senha, pontos)
    values ('peach', 'princesa@yahoo.com', 'Peach', 'socorro', 3)
;

insert into topico (login, titulo, conteudo)
    values ('mario', 'Corrida?', 'Quem está com ânimo para uma corrida no Mario Kart?')
;

insert into topico (login, titulo, conteudo)
    values ('luigi', 'Mario Verde uma ova!!!', 'O próximo que me chamar de Mario Verde, está ferrado')
;

insert into comentario (login, comentario, id_topico)
    values ('peach', 'Bora!', 1)
;

insert into comentario (login, comentario, id_topico)
    values ('mario', 'Não fica bravo, Mario Verde!', 2)
;

