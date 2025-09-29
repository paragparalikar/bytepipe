import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-created-by',
  imports: [DatePipe],
  templateUrl: './created-by.component.html',
  styleUrl: './created-by.component.css'
})
export class CreatedByComponent implements OnChanges {

  user?: User|null;
  @Input({required: true}) text?: string|null;
  @Input({required: true}) userId?: string|null;
  @Input({required: true}) date?: Date|null;

  constructor(private userService: UserService){}

  ngOnChanges(changes: SimpleChanges): void {
    if(this.userId){
      this.userService.findById(this.userId).subscribe(user => {
        this.user = user;
      });
    }
  }

}
