import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CategoryComponent } from './category.component';
import { CategoryDetailComponent } from './category-detail.component';
import { CategoryPopupComponent } from './category-dialog.component';
import { CategoryDeletePopupComponent } from './category-delete-dialog.component';

import { Principal } from '../../shared';

export const categoryRoute: Routes = [
    {
        path: 'category',
        component: CategoryComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MODERATOR', 'ROLE_SMM'],
            pageTitle: 'dtoApp.category.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category/:id',
        component: CategoryDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.category.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryPopupRoute: Routes = [
    {
        path: 'category-new',
        component: CategoryPopupComponent,
        data: {
           authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.category.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category/:id/edit',
        component: CategoryPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'dtoApp.category.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category/:id/delete',
        component: CategoryDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.category.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
