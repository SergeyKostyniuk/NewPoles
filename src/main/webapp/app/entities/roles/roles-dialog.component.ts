import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Roles } from './roles.model';
import { RolesPopupService } from './roles-popup.service';
import { RolesService } from './roles.service';
import { Manager, ManagerService } from '../manager';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-roles-dialog',
    templateUrl: './roles-dialog.component.html'
})
export class RolesDialogComponent implements OnInit {

    roles: Roles;
    authorities: any[];
    isSaving: boolean;

    managers: Manager[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private rolesService: RolesService,
        private managerService: ManagerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MODERATOR', 'ROLE_SMM'];
        this.managerService.query()
            .subscribe((res: ResponseWrapper) => { this.managers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.roles.id !== undefined) {
            this.subscribeToSaveResponse(
                this.rolesService.update(this.roles), false);
        } else {
            this.subscribeToSaveResponse(
                this.rolesService.create(this.roles), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Roles>, isCreated: boolean) {
        result.subscribe((res: Roles) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Roles, isCreated: boolean) {
        this.alertService.success(
            isCreated ? 'dtoApp.roles.created'
            : 'dtoApp.roles.updated',
            { param : result.id }, null);

        this.eventManager.broadcast({ name: 'rolesListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackManagerById(index: number, item: Manager) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-roles-popup',
    template: ''
})
export class RolesPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private rolesPopupService: RolesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.rolesPopupService
                    .open(RolesDialogComponent, params['id']);
            } else {
                this.modalRef = this.rolesPopupService
                    .open(RolesDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
