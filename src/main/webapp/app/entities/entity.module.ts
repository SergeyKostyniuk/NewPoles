import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DtoCustomerModule } from './customer/customer.module';
import { DtoLoginOptionsModule } from './login-options/login-options.module';
import { DtoStaticPagesModule } from './static-pages/static-pages.module';
import { DtoManagerModule } from './manager/manager.module';
import { DtoBucketModule } from './bucket/bucket.module';
import { DtoAddressShippingModule } from './address-shipping/address-shipping.module';
import { DtoSeenModule } from './seen/seen.module';
import { DtoWishListModule } from './wish-list/wish-list.module';
import { DtoAddressModule } from './address/address.module';
import { DtoAvatarModule } from './avatar/avatar.module';
import { DtoPersonalInformationModule } from './personal-information/personal-information.module';
import { DtoProductsModule } from './products/products.module';
import { DtoTagsModule } from './tags/tags.module';
import { DtoConsignmentModule } from './consignment/consignment.module';
import { DtoStorageModule } from './storage/storage.module';
import { DtoProducersModule } from './producers/producers.module';
import { DtoCategoryModule } from './category/category.module';
import { DtoSubCategoryModule } from './sub-category/sub-category.module';
import { DtoMediaModule } from './media/media.module';
import { DtoCommentsModule } from './comments/comments.module';
import { DtoOptionsModule } from './options/options.module';
import { DtoTagForProductModule } from './tag-for-product/tag-for-product.module';
import { DtoProductInBucketModule } from './product-in-bucket/product-in-bucket.module';
import { DtoRolesModule } from './roles/roles.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        DtoCustomerModule,
        DtoLoginOptionsModule,
        DtoStaticPagesModule,
        DtoManagerModule,
        DtoBucketModule,
        DtoAddressShippingModule,
        DtoSeenModule,
        DtoWishListModule,
        DtoAddressModule,
        DtoAvatarModule,
        DtoPersonalInformationModule,
        DtoProductsModule,
        DtoTagsModule,
        DtoConsignmentModule,
        DtoStorageModule,
        DtoProducersModule,
        DtoCategoryModule,
        DtoSubCategoryModule,
        DtoMediaModule,
        DtoCommentsModule,
        DtoOptionsModule,
        DtoTagForProductModule,
        DtoProductInBucketModule,
        DtoRolesModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtoEntityModule {}
