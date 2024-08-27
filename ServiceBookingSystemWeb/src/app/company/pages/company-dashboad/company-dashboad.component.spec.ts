import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyDashboadComponent } from './company-dashboad.component';

describe('CompanyDashboadComponent', () => {
  let component: CompanyDashboadComponent;
  let fixture: ComponentFixture<CompanyDashboadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CompanyDashboadComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyDashboadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
