import { BaseEntity } from './../../shared';

export class Manager implements BaseEntity {
    constructor(
        public id?: number,
        public loginOptionsId?: number,
        public personalInfoId?: number,
        public avatarId?: number,
        public manegers?: BaseEntity[],
        public roles?: BaseEntity[],
    ) {
    }
}
