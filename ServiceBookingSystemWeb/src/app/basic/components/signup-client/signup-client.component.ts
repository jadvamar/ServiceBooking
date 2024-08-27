import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../Services/Auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-client',
  templateUrl: './signup-client.component.html',
  styleUrls: ['./signup-client.component.scss']
})
export class SignupClientComponent implements OnInit {

  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private notification: NzNotificationService,
              private router: Router) { }

  ngOnInit() {
    this.validateForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      name: [null, [Validators.required]],
      lastname: [null, [Validators.required]],
      phone: [null],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required]],
    });
  }

  submitForm(){
    this.authService.registerClient(this.validateForm.value).subscribe(res =>{
      this.notification.success(
        'SUCCESS','Signup succesfull',{nzDuration:5000}
      );
      this.router.navigateByUrl('/login');
    },error =>{
      this.notification.error(
        'ERROR',`${error.error}`,{nzDuration:5000}
      );
    });
  }
}
