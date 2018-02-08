import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  registerForm: FormGroup;
  btnClicked: boolean = false;
  selCity : boolean = false;

  constructor(public navCtrl: NavController, public formBuilder: FormBuilder, private toastCtrl: ToastController) {
    this.registerForm = formBuilder.group({
      register_name: ['', Validators.compose([Validators.required, Validators.pattern("[a-zA-Z '-]+$")])],
      register_email: ['', Validators.compose([Validators.required, Validators.pattern('[A-Za-z0-9._%+-]{3,}@[a-zA-Z]{3,}([.]{1}[a-zA-Z]{2,}|[.]{1}[a-zA-Z]{2,}[.]{1}[a-zA-Z]{2,})')])],
      register_phone: ['', Validators.compose([Validators.required, Validators.pattern("[0-9]*")])],
      register_city: ['', Validators.compose([Validators.required])]
    });
  }

  onSubmit(value: any): void {
    this.btnClicked = true;
    if (this.registerForm.valid) {
      let toast = this.toastCtrl.create();
      toast.setDuration(3000);
      toast.setPosition("middle");
      toast.setMessage("Validation Success");
      toast.present();
    } else {
      console.log("Invalid Form data")
    }
  }

  onChangeName(data): void {
    //console.log(data.search(/[^a-zA-Z '-]+/));
    if (data.search(/[^a-zA-Z '-]+/) === 0) {
      this.btnClicked = true;
    }
  }

  onChangePhone(data): void {
    //console.log(data.search(/[^0-9]*/));
    if (data.search(/[^0-9]*/) === 0) {
      this.btnClicked = true;
    }
  }

  onChangeEmail(data): void {
    //console.log(data.search(/[^[A-Za-z0-9._%+-]{3,}@[a-zA-Z]{3,}([.]{1}[a-zA-Z]{2,}|[.]{1}[a-zA-Z]{2,}[.]{1}[a-zA-Z]{2,})]/));
    if (data.search(/[^[A-Za-z0-9._%+-]{3,}@[a-zA-Z]{3,}([.]{1}[a-zA-Z]{2,}|[.]{1}[a-zA-Z]{2,}[.]{1}[a-zA-Z]{2,})]/) === 0) {
      this.btnClicked = true;
    }
  }

  onChangeCity(selectedValue: any){
    if(selectedValue == ""){
      this.selCity = true;
    }
  }

}
