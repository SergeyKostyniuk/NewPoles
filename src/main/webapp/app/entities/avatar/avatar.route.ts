import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AvatarComponent } from './avatar.component';
import { AvatarDetailComponent } from './avatar-detail.component';
import { AvatarPopupComponent } from './avatar-dialog.component';
import { AvatarDeletePopupComponent } from './avatar-delete-dialog.component';

import { Principal } from '../../shared';

export const avatarRoute: Routes = [
    {
        path: 'avatar',
        component: AvatarComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MODERATOR', 'ROLE_SMM'],
            pageTitle: 'dtoApp.avatar.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'avatar/:id',
        component: AvatarDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MODERATOR', 'ROLE_SMM'],
            pageTitle: 'dtoApp.avatar.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const avatarPopupRoute: Routes = [
    {
        path: 'avatar-new',
        component: AvatarPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_MODERATOR'],
            pageTitle: 'dtoApp.avatar.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'avatar/:id/edit',
        component: AvatarPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_SMM'],
            pageTitle: 'dtoApp.avatar.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'avatar/:id/delete',
        component: AvatarDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtoApp.avatar.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
