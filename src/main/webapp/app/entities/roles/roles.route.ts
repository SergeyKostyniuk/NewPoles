import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RolesComponent } from './roles.component';
import { RolesDetailComponent } from './roles-detail.component';
import { RolesPopupComponent } from './roles-dialog.component';
import { RolesDeletePopupComponent } from './roles-delete-dialog.component';

import { Principal } from '../../shared';

export const rolesRoute: Routes = [
    {
        path: 'roles',
        component: RolesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.roles.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'roles/:id',
        component: RolesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.roles.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rolesPopupRoute: Routes = [
    {
        path: 'roles-new',
        component: RolesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.roles.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'roles/:id/edit',
        component: RolesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.roles.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'roles/:id/delete',
        component: RolesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.roles.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
