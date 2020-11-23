import { Todo } from './todo';

export class TodoPagedList {
    content: Todo[];

    number: number;
    size: number;
    totalElements: number;
    last: boolean;
    totalPages: number;
    first: boolean;
    numberOfElements: number;
}
