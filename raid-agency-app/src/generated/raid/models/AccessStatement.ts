/* tslint:disable */
/* eslint-disable */
/**
 * RAID v2 API
 * This file is where all the endpoint paths are defined, it\'s the \"top level\' of the OpenAPI definition that links all the different files together. The `3.0` in the filename refers to this file being based on OpenAPI 3.0  as opposed to OpenAPI 3.1, which the tooling doesn\'t support yet. The `2.0.0` in the version field refers to the fact that there\'s already  a `1.0.0` used for the legacy RAiD application. Note that swagger ui doesn\'t currently work with our spec,  see https://github.com/swagger-api/swagger-ui/issues/7724 But the spec works fine with openapi-generator tooling. 
 *
 * The version of the OpenAPI document: 2.0.0
 * Contact: contact@raid.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
import type { Language } from './Language';
import {
    LanguageFromJSON,
    LanguageFromJSONTyped,
    LanguageToJSON,
} from './Language';

/**
 * 
 * @export
 * @interface AccessStatement
 */
export interface AccessStatement {
    /**
     * 
     * @type {string}
     * @memberof AccessStatement
     */
    text?: string;
    /**
     * 
     * @type {Language}
     * @memberof AccessStatement
     */
    language?: Language;
}

/**
 * Check if a given object implements the AccessStatement interface.
 */
export function instanceOfAccessStatement(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function AccessStatementFromJSON(json: any): AccessStatement {
    return AccessStatementFromJSONTyped(json, false);
}

export function AccessStatementFromJSONTyped(json: any, ignoreDiscriminator: boolean): AccessStatement {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'text': !exists(json, 'text') ? undefined : json['text'],
        'language': !exists(json, 'language') ? undefined : LanguageFromJSON(json['language']),
    };
}

export function AccessStatementToJSON(value?: AccessStatement | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'text': value.text,
        'language': LanguageToJSON(value.language),
    };
}

