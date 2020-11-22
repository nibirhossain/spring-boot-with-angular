import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';
import { Todo } from '../shared/model/todo';
 
@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  todos: Todo[];
  message: string;

  constructor(
    private todoDataService: TodoDataService,
    private router : Router
  ) { }

  ngOnInit() {
    this.refreshTodos();
  }

  refreshTodos(){
    this.todoDataService.retrieveAllTodos().subscribe(
      response => {
        // console.log(response);
        this.todos = response;
      }
    )
  }

  deleteTodo(id: number) {
    console.log(`Delete todo ${id}`);
    this.todoDataService.deleteTodo(id).subscribe(
      response => {
        console.log(response);
        this.message = `Delete of todo ${id} successfull!`;
        this.refreshTodos();
      }
    )
  }

  updateTodo(id: number) {
    console.log(`Update of todo ${id} successfull!`);
    this.router.navigate(['todos', id]);
  }

}
