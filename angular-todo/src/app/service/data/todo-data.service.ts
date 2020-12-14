import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Todo } from '../../shared/model/todo'
import { TodoPagedList} from '../../shared/model/todo-paged-list'
import { Observable } from 'rxjs';

const API_BASE_URL: string = 'http://localhost:8080/api/v1';

@Injectable({
  providedIn: 'root'
})

export class TodoDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllTodosWithPagination(params: any) {
    console.log("Retrieve all todos with pagination!!!");
    return this.http.get<TodoPagedList>(`${API_BASE_URL}/todos`, {params: params});
  }  

  retrieveAllTodos() {
    console.log("Retrieve all todos!!!");
    return this.http.get<Todo[]>(`${API_BASE_URL}/todos-normal`);
  }

  retrieveTodo(id: number) {
    return this.http.get<Todo>(`${API_BASE_URL}/todos/${id}`);
  }

  deleteTodo(id: number) {
    return this.http.delete(`${API_BASE_URL}/todos/${id}`)
  }

  updateTodo(id: number, todo: Todo){
    return this.http.put(`${API_BASE_URL}/todos/${id}`, todo);
  }

  createTodo(todo: Todo){
    return this.http.post(`${API_BASE_URL}/todos`, todo);
  }

}
