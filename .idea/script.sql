select lv.id,aut.id,aut.data_nascimento,aut.nacionalidade,aut.nome,lv.data_publicacao,lv.genero,lv.isbn,lv.preco,lv.titulo
from livro lv
left join public.autor aut on aut.id=lv.id_autor
where lv.id=?