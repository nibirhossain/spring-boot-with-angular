import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Todo } from '../../shared/model/todo'

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllTodos() {
    return this.http.get<Todo[]>(`http://localhost:8080/todos`);
    console.log("Retrieve all todos!!!");
  }

  retrieveTodo(id: number) {
    return this.http.get<Todo>(`http://localhost:8080/todos/${id}`);
  }

  deleteTodo(id: number) {
    return this.http.delete(`http://localhost:8080/todos/${id}`)
  }

  updateTodo(id: number, todo: Todo){
    return this.http.put(`http://localhost:8080/todos/${id}`, todo);
  }

  createTodo(todo: Todo){
    return this.http.post(`http://localhost:8080/todos`, todo);
  }

}
