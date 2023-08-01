package org.eclipse.jakarta.hello;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.jakarta.hello.dtos.*;
import org.eclipse.jakarta.hello.dtos.payload.CreateUserPayload;
import org.eclipse.jakarta.hello.dtos.payload.LoginPayload;
import org.eclipse.jakarta.hello.dtos.payload.UpdateUserPayload;
import org.eclipse.jakarta.hello.dtos.payload.UpdateUserRolesPayload;
import org.eclipse.jakarta.hello.services.UserService;

import java.util.List;

@Path("/users")
public class UserResource {

	@Inject
	private UserService userService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<UserDto> listUsers() {
		return userService.listUsers();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDto getUserById(@PathParam("id") Long userId) {
		return userService.getUserById(userId);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDto createUser(@Valid CreateUserPayload payload) {
		return userService.createUser(payload);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDto updateUser(@Valid UpdateUserPayload payload) {
		return userService.updateUser(payload);
	}

	@PUT
	@Path("/roles")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDto updateUseRoles(@Valid UpdateUserRolesPayload payload) {
		return userService.updateUserRoles(payload);
	}

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDto login(@Valid LoginPayload payload) {
		return userService.login(payload);
	}

	@GET
	@Path("/block/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserDto blockUser(@PathParam("id") Long userId, @QueryParam("block") boolean block) {
		return userService.blockUser(userId, block);
	}
}