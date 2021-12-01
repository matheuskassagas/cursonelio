package com.cursonelio.javaspringboot.cursoNelio.controller;


import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Categoria;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFounfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursonelio.javaspringboot.cursoNelio.service.CategoriaService;

import java.util.List;

@RestController//classe controladora
@RequestMapping(value="/categorias")//nome do end point rest
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    //simples requisicao GET - verbo http obtendo dados (pegando dados)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        try{
            Categoria obj = categoriaService.find(id);
            return ResponseEntity.ok().body(obj);
        }catch (ObjectNotFounfException e){
            throw new ObjectNotFounfException(e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> findAll(){
        List<Categoria> categoria = categoriaService.findAll();
        return categoria;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody  Categoria categoria){
        categoria = categoriaService.create(categoria);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Categoria update(@PathVariable Integer id, @RequestBody Categoria categoria){
        return categoriaService.update(id, categoria);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        categoriaService.delete(id);
    }

}
