package com.christiandias.cursomc.repositories;

import java.util.List;

import com.christiandias.cursomc.domain.Categoria;
import com.christiandias.cursomc.domain.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    // Achamos os padrões de definições de nomes de métodos para servir como queries pelo site
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
    @Transactional(readOnly =true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,List<Categoria>categorias,Pageable pageRequest);
    
    
    /*
    Podemos definir a procurar definindo a query a ser utilizada no JPQL, e informando os paramentros pela anotação
    ou usar o padrão a cima em que pelo nome do próprio método a própria framework irá realizar a operação
     @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> searcg(@Param("nome") String nome,@Param("categorias") List<Categoria>categorias,Pageable pageRequest);
	*/
}
