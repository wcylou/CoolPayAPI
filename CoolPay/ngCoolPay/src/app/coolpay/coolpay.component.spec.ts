import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoolpayComponent } from './coolpay.component';

describe('CoolpayComponent', () => {
  let component: CoolpayComponent;
  let fixture: ComponentFixture<CoolpayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoolpayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoolpayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
