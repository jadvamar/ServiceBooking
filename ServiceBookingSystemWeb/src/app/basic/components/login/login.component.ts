import { Component } from '@angular/core';
import { AuthService } from '../../Services/Auth/auth.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from 'express';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router,
  ){

  }

  ngonInit() {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
    })
  }
    submitForm(){
        this.authService.login(this.validateForm.get(['userName'])!.value, this.validateForm.get(['password'])!.value)
        .subscribe(res => {
        console.log(res)
        }, error =>{
        this.notification
        .error(
        'ERROR',
        `Bad crendentials`,
        {nzDuration: 5000}
        )
      })
    }
  
}
