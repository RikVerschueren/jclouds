/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.savvis.vpdc;

import java.net.URI;

import javax.annotation.Nullable;
import javax.ws.rs.GET;

import org.jclouds.rest.annotations.EndpointParam;
import org.jclouds.rest.annotations.ExceptionParser;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.XMLResponseParser;
import org.jclouds.rest.functions.ReturnNullOnNotFoundOr404;
import org.jclouds.savvis.vpdc.xml.SymphonyVPDCNetworkHandler;
import org.jclouds.savvis.vpdc.xml.SymphonyVPDCVAppHandler;
import org.jclouds.vcloud.CommonVCloudClient;
import org.jclouds.vcloud.VCloudExpressAsyncClient;
import org.jclouds.vcloud.domain.Org;
import org.jclouds.vcloud.domain.VCloudExpressVApp;
import org.jclouds.vcloud.domain.VDC;
import org.jclouds.vcloud.domain.network.OrgNetwork;
import org.jclouds.vcloud.filters.SetVCloudTokenCookie;
import org.jclouds.vcloud.functions.OrgNameAndVDCNameToEndpoint;
import org.jclouds.vcloud.functions.OrgNameToEndpoint;
import org.jclouds.vcloud.functions.OrgNameVDCNameResourceEntityNameToEndpoint;
import org.jclouds.vcloud.xml.OrgHandler;
import org.jclouds.vcloud.xml.VDCHandler;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Provides access to Symphony VPDC resources via their REST API.
 * <p/>
 * 
 * @see <a href="TODO PUBLIC DOC REF" />
 * @author Adrian Cole
 */
@RequestFilters(SetVCloudTokenCookie.class)
public interface SymphonyVPDCAsyncClient extends VCloudExpressAsyncClient {

   /**
    * {@inheritDoc}
    */
   // savvis doesn't work with accept header
   @Override
   @GET
   @XMLResponseParser(OrgHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends Org> findOrgNamed(
            @Nullable @EndpointParam(parser = OrgNameToEndpoint.class) String orgName);

   /**
    * {@inheritDoc}
    */
   // savvis doesn't work with accept header
   @Override
   @GET
   @XMLResponseParser(OrgHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends Org> getOrg(@EndpointParam URI orgId);
   
   /**
    * @see CommonVCloudClient#getVDC(URI)
    */
   @GET
   @XMLResponseParser(VDCHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends VDC> getVDC(@EndpointParam URI vdc);

   /**
    * @see CommonVCloudClient#findVDCInOrgNamed(String, String)
    */
   @GET
   @XMLResponseParser(VDCHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends VDC> findVDCInOrgNamed(
            @Nullable @EndpointParam(parser = OrgNameAndVDCNameToEndpoint.class) String orgName,
            @Nullable @EndpointParam(parser = OrgNameAndVDCNameToEndpoint.class) String vdcName);
   
   /**
    * @see CommonVCloudClient#findNetworkInOrgVDCNamed
    *//*
   @GET
   @XMLResponseParser(TerremarkOrgNetworkFromTerremarkVCloudExpressNetworkHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends OrgNetwork> findNetworkInOrgVDCNamed(
            @Nullable @EndpointParam(parser = OrgNameVDCNameResourceEntityNameToEndpoint.class) String orgName,
            @Nullable @EndpointParam(parser = OrgNameVDCNameResourceEntityNameToEndpoint.class) String catalogName,
            @EndpointParam(parser = OrgNameVDCNameResourceEntityNameToEndpoint.class) String networkName);

   *//**
    * @see CommonVCloudClient#getNetwork
    *//*
   @GET
   @XMLResponseParser(TerremarkOrgNetworkFromTerremarkVCloudExpressNetworkHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends OrgNetwork> getNetwork(@EndpointParam URI network);*/
   
   /**
    * @see CommonVCloudClient#findNetworkInOrgVDCNamed
    */
   @GET
   @XMLResponseParser(SymphonyVPDCNetworkHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends OrgNetwork> findNetworkInOrgVDCNamed(
            @Nullable @EndpointParam(parser = OrgNameVDCNameResourceEntityNameToEndpoint.class) String orgName,
            @Nullable @EndpointParam(parser = OrgNameVDCNameResourceEntityNameToEndpoint.class) String catalogName,
            @EndpointParam(parser = OrgNameVDCNameResourceEntityNameToEndpoint.class) String networkName);

   /**
    * @see CommonVCloudClient#getNetwork
    */
   @GET
   @XMLResponseParser(SymphonyVPDCNetworkHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends OrgNetwork> getNetwork(@EndpointParam URI network);
   
   /**
    * @see VCloudClient#getVApp
    */
   @GET
   @XMLResponseParser(SymphonyVPDCVAppHandler.class)
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<? extends VCloudExpressVApp> getVApp(@EndpointParam URI vApp);
   
}