package com.diego.todolist.controller;


import com.diego.todolist.model.Task;
import com.diego.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {


    TaskService taskService;

    @ApiOperation(value = "Criando uma nova tarefa")
    @ApiResponses(value ={
            @ApiResponse(code= 201, message = "Tarefa criada com sucesso"),
            @ApiResponse(code= 500, message = "Houve um erro ao criar a tarefa, verifique as informações")

    } )

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        log.info("Criando uma nova tarefa com as informações [{}]",task);
        return taskService.createdTask(task);
    }



    @ApiOperation(value = "Listando todas as tarefas")
    @ApiResponses(value ={
            @ApiResponse(code= 200, message = "Tarefa listadas com sucesso"),
            @ApiResponse(code= 500, message = "Houve um erro ao criar ao listar as tarefas")

    } )
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }


    @ApiOperation(value = "Buscando uma tarefa pelo id")
    @ApiResponses(value ={
            @ApiResponse(code= 200, message = "Tarefa encontrada com sucesso"),
            @ApiResponse(code= 404, message = "Não foi encontrada nenhuma tarefa com esse id")

    } )
    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable(value ="id") Long id ){
        log.info("Buscando tarega com o id [{}]",id);
        return taskService.findTaskById(id);
    }



    @ApiOperation(value = "atualizando uma tarefa")
    @ApiResponses(value ={
            @ApiResponse(code= 200, message = "Tarefa atualizada com sucesso"),
            @ApiResponse(code= 404, message = "Não foi poissivel atualizar a tarefa - tarefa não encontrada")

    } )
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable(value ="id") Long id,@RequestBody Task task ){
        log.info("Atualizando a tarefa com id [{}] as novas informações são : [{}]",id,task);

        return taskService.updateTaskById(task,id);
    }



    @ApiOperation(value = "Excluindo a tarefa")
    @ApiResponses(value ={
            @ApiResponse(code= 204, message = "Tarefa excluida com sucesso"),
            @ApiResponse(code= 404, message = "Não foi poissivel excluir a tarefa - tarefa não encontrada")

    } )
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable(value ="id") Long id ){
        log.info("Excluindo tarefas com  o id [{}]",id);

        return taskService.deleteById(id);
    }



}
