package io.github.cauarb.rest;

import io.github.cauarb.model.entity.Cliente;
import io.github.cauarb.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteContoller {

    private final ClienteRepository repository;

    @Autowired
    public ClienteContoller (ClienteRepository repository) {
        this.repository = repository;
    }
    @PostMapping  // mapea o metodo
    @ResponseStatus(HttpStatus.CREATED) //indica a criação de recurso no servidor
    public Cliente salvar(@RequestBody @Valid Cliente cliente) { // conventendo objeto em JSON para o corpo da requisição
        return repository.save(cliente);
    }
    @GetMapping("{id}") // obter recursos do servidor
    public Cliente acharPorId(@PathVariable Integer id){ // informações do clintes atraves do id
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") ); // se achar codigo de status 200 senão codigo 404 not found
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id) // buscar cliente
                .map( cliente -> {
                    repository.delete(cliente); //deletar
                    return Void.TYPE; // sem retorno
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }
    @PutMapping("{id}") // atualizar recurso
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
        repository
                .findById(id) // buscar cliente
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }
}
