import { NgFor, NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { AbstractControl, AbstractControlDirective } from '@angular/forms';

@Component({
    selector: 'form-error',
    imports: [NgIf],
    templateUrl: 'form-error.component.html',
    styleUrls: ['form-error.component.css']
})

export class ErrorComponent {

    @Input() name?: string|null;
    @Input() control?: AbstractControl | AbstractControlDirective

}