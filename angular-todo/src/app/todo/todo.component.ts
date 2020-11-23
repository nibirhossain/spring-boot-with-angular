import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';
import { Todo } from '../shared/model/todo';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  id: number;
  todo: Todo;

  constructor(
    private todoService: TodoDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.todo = new Todo();

    this.todoService.retrieveTodo(this.id).subscribe(
      response => {
        console.log(response);
        this.todo = response;
      }
    )
  }

  saveTodo() {
    if(this.id == 0) { //=== ==
      this.todoService.createTodo(this.todo)
          .subscribe (
            response => {
              console.log(response);
              console.log("Test");
              this.router.navigate(['todos']);
            }
          )
    } else {
      this.todoService.updateTodo(this.id, this.todo)
          .subscribe (
            response => {
              console.log(response)
              this.router.navigate(['todos'])
            }
          )
    }
  }

}
