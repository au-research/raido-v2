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


import * as runtime from '../runtime';
import type {
  ServicePoint,
  ServicePointCreateRequest,
  ServicePointUpdateRequest,
  ValidationFailureResponse,
} from '../models/index';
import {
    ServicePointFromJSON,
    ServicePointToJSON,
    ServicePointCreateRequestFromJSON,
    ServicePointCreateRequestToJSON,
    ServicePointUpdateRequestFromJSON,
    ServicePointUpdateRequestToJSON,
    ValidationFailureResponseFromJSON,
    ValidationFailureResponseToJSON,
} from '../models/index';

export interface CreateServicePointRequest {
    servicePointCreateRequest: ServicePointCreateRequest;
}

export interface FindServicePointByIdRequest {
    id: number;
}

export interface UpdateServicePointRequest {
    id: number;
    servicePointUpdateRequest: ServicePointUpdateRequest;
}

/**
 * 
 */
export class ServicePointApi extends runtime.BaseAPI {

    /**
     */
    async createServicePointRaw(requestParameters: CreateServicePointRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<ServicePoint>> {
        if (requestParameters.servicePointCreateRequest === null || requestParameters.servicePointCreateRequest === undefined) {
            throw new runtime.RequiredError('servicePointCreateRequest','Required parameter requestParameters.servicePointCreateRequest was null or undefined when calling createServicePoint.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("bearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/service-point/`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: ServicePointCreateRequestToJSON(requestParameters.servicePointCreateRequest),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => ServicePointFromJSON(jsonValue));
    }

    /**
     */
    async createServicePoint(requestParameters: CreateServicePointRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<ServicePoint> {
        const response = await this.createServicePointRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async findAllServicePointsRaw(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<ServicePoint>>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("bearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/service-point/`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(ServicePointFromJSON));
    }

    /**
     */
    async findAllServicePoints(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<ServicePoint>> {
        const response = await this.findAllServicePointsRaw(initOverrides);
        return await response.value();
    }

    /**
     */
    async findServicePointByIdRaw(requestParameters: FindServicePointByIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<ServicePoint>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling findServicePointById.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("bearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/service-point/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => ServicePointFromJSON(jsonValue));
    }

    /**
     */
    async findServicePointById(requestParameters: FindServicePointByIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<ServicePoint> {
        const response = await this.findServicePointByIdRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     */
    async updateServicePointRaw(requestParameters: UpdateServicePointRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<ServicePoint>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling updateServicePoint.');
        }

        if (requestParameters.servicePointUpdateRequest === null || requestParameters.servicePointUpdateRequest === undefined) {
            throw new runtime.RequiredError('servicePointUpdateRequest','Required parameter requestParameters.servicePointUpdateRequest was null or undefined when calling updateServicePoint.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = await token("bearerAuth", []);

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/service-point/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: ServicePointUpdateRequestToJSON(requestParameters.servicePointUpdateRequest),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => ServicePointFromJSON(jsonValue));
    }

    /**
     */
    async updateServicePoint(requestParameters: UpdateServicePointRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<ServicePoint> {
        const response = await this.updateServicePointRaw(requestParameters, initOverrides);
        return await response.value();
    }

}