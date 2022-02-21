import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatlistComponent } from './boatlist.component';

describe('BoatlistComponent', () => {
  let component: BoatlistComponent;
  let fixture: ComponentFixture<BoatlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatlistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
