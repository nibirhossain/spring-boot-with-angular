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
  currentTodo: Todo;

  currentIndex = -1;
  page = 1;
  count = 0;
  pageSize = 2;
  pageSizes = [2, 4, 6];

  constructor(
    private todoDataService: TodoDataService,
    private router : Router
  ) { }

  ngOnInit() {
    this.refreshTodos();
  }

  refreshTodos(){
    const params = this.getRequestParams(this.page, this.pageSize);

    this.todoDataService.retrieveAllTodosWithPagination(params)
      .subscribe(
        response => {
          // const { tutorials, totalItems } = response;
          // this.tutorials = tutorials;
          // this.count = totalItems;
          this.count = response.totalElements;
          this.todos = response.content;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  getRequestParams(page, pageSize) {
    let params = {};

    if (page) {
      params[`pageNumber`] = page - 1;
    }

    if (pageSize) {
      params[`pageSize`] = pageSize;
    }

    return params;
  }

  handlePageChange(event) {
    this.page = event;
    this.refreshTodos();
  }

  handlePageSizeChange(event) {
    this.pageSize = event.target.value;
    this.page = 1;
    this.refreshTodos();
  }

  setActiveTodo(todo: Todo, index: number) {
    console.log(todo);
    this.currentTodo = todo;
    this.currentIndex = index;
  }

  addTodo() {
    this.router.navigate(['todos', 0]);
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
