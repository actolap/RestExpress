package com.strategicgains.restx.route;

import static org.junit.Assert.*;

import java.util.List;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.junit.BeforeClass;
import org.junit.Test;

import com.strategicgains.restx.Request;
import com.strategicgains.restx.Response;

/**
 * @author toddf
 * @since Sep 27, 2010
 */
public class RouteMappingTest
{
	/**
     * 
     */
    private static final String RAH_ROUTE_NAME = "POST_ONLY";
	private static Routes routes;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		routes = new Routes();
	}

	@Test
	public void testGetRoutesForNullMethod()
	{
		routes.getRoutesFor(null);
	}
	
	@Test
	public void testGetRoutesForGetMethod()
	{
		List<Route> r = routes.getRoutesFor(HttpMethod.GET);
		assertNotNull(r);
		assertFalse(r.isEmpty());
		assertEquals(4, r.size());
	}
	
	@Test
	public void testGetRoutesForPutMethod()
	{
		List<Route> r = routes.getRoutesFor(HttpMethod.PUT);
		assertNotNull(r);
		assertFalse(r.isEmpty());
		assertEquals(1, r.size());
	}
	
	@Test
	public void testGetRoutesForPostMethod()
	{
		List<Route> r = routes.getRoutesFor(HttpMethod.POST);
		assertNotNull(r);
		assertFalse(r.isEmpty());
		assertEquals(3, r.size());
	}
	
	@Test
	public void testGetRoutesForDeleteMethod()
	{
		List<Route> r = routes.getRoutesFor(HttpMethod.DELETE);
		assertNotNull(r);
		assertFalse(r.isEmpty());
		assertEquals(1, r.size());
	}
	
	@Test
	public void testGetRoutesForHeadMethod()
	{
		List<Route> r = routes.getRoutesFor(HttpMethod.HEAD);
		assertNotNull(r);
		assertTrue(r.isEmpty());
	}

	@Test
	public void testGetNullNamedRoute()
	{
		Route r = routes.getNamedRoute(null, HttpMethod.GET);
		assertNull(r);
	}

	@Test
	public void shouldReturnNullForNamedRouteWithWrongMethod()
	{
		Route r = routes.getNamedRoute(RAH_ROUTE_NAME, HttpMethod.GET);
		assertNull(r);
	}

	@Test
	public void shouldGetReplyToNamedRoute()
	{
		Route r = routes.getNamedRoute(RAH_ROUTE_NAME, HttpMethod.POST);
		assertNotNull(r);
		assertEquals(RAH_ROUTE_NAME, r.getName());
		assertEquals("createRah", r.getAction().getName());
	}

	@Test
	public void shouldGetCRUDReadNamedRoute()
	{
		Route r = routes.getNamedRoute("CRUD_ROUTE", HttpMethod.GET);
		assertNotNull(r);
		assertEquals("CRUD_ROUTE", r.getName());
		assertEquals("read", r.getAction().getName());
	}

	@Test
	public void shouldGetCRUDCreateNamedRoute()
	{
		Route r = routes.getNamedRoute("CRUD_ROUTE", HttpMethod.POST);
		assertNotNull(r);
		assertEquals("CRUD_ROUTE", r.getName());
		assertEquals("create", r.getAction().getName());
	}

	@Test
	public void shouldGetCRUDUpdateNamedRoute()
	{
		Route r = routes.getNamedRoute("CRUD_ROUTE", HttpMethod.PUT);
		assertNotNull(r);
		assertEquals("CRUD_ROUTE", r.getName());
		assertEquals("update", r.getAction().getName());
	}

	@Test
	public void shouldGetCRUDDeleteNamedRoute()
	{
		Route r = routes.getNamedRoute("CRUD_ROUTE", HttpMethod.DELETE);
		assertNotNull(r);
		assertEquals("CRUD_ROUTE", r.getName());
		assertEquals("delete", r.getAction().getName());
	}
	
	private static class Routes
	extends RouteMapping
	{
		private InnerService service;

        @Override
        protected void defineRoutes()
        {
        	service = new InnerService();

    		uri("/foo/bar/{barId}.{format}", service)
    			.action("readBar", HttpMethod.GET);

    		uri("/foo/bat/{batId}.{format}", service)
    			.action("readBat", HttpMethod.GET);

    		uri("/foo.{format}", service)
    			.method(HttpMethod.POST);

    		uri("/foo/{fooId}.{format}", service)
    			.name("CRUD_ROUTE");

    		uri("/foo/rah/{rahId}.{format}", service)
    			.action("createRah", HttpMethod.POST)
    			.name(RAH_ROUTE_NAME);

    		uri("/foo/yada/{yadaId}.{format}", service)
    			.action("readYada", HttpMethod.GET);
        }
		
	}
	
	private static class InnerService
	{
		@SuppressWarnings("unused")
        public Object create(Request request, Response response)
		{
			return null;
		}

		@SuppressWarnings("unused")
        public Object read(Request request, Response response)
		{
			return null;
		}

		@SuppressWarnings("unused")
        public void update(Request request, Response response)
		{
		}

		@SuppressWarnings("unused")
		public void delete(Request request, Response response)
		{
		}

		@SuppressWarnings("unused")
		public Object createRah(Request request, Response response)
		{
			return null;
		}

		@SuppressWarnings("unused")
		public Object readBar(Request request, Response response)
		{
			return null;
		}

		@SuppressWarnings("unused")
		public Object readBat(Request request, Response response)
		{
			return null;
		}

		@SuppressWarnings("unused")
		public Object readYada(Request request, Response response)
		{
			return null;
		}
	}
}
