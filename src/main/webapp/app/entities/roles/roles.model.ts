import { BaseEntity } from './../../shared';

export class Roles implements BaseEntity {
    constructor(
        public id?: number,
        public role?: string,
        public managerId?: number,
    ) {
    }
}
