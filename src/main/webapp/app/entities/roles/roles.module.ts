import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtoSharedModule } from '../../shared';
import {
    RolesService,
    RolesPopupService,
    RolesComponent,
    RolesDetailComponent,
    RolesDialogComponent,
    RolesPopupComponent,
    RolesDeletePopupComponent,
    RolesDeleteDialogComponent,
    rolesRoute,
    rolesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...rolesRoute,
    ...rolesPopupRoute,
];

@NgModule({
    imports: [
        DtoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RolesComponent,
        RolesDetailComponent,
        RolesDialogComponent,
        RolesDeleteDialogComponent,
        RolesPopupComponent,
        RolesDeletePopupComponent,
    ],
    entryComponents: [
        RolesComponent,
        RolesDialogComponent,
        RolesPopupComponent,
        RolesDeleteDialogComponent,
        RolesDeletePopupComponent,
    ],
    providers: [
        RolesService,
        RolesPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtoRolesModule {}
