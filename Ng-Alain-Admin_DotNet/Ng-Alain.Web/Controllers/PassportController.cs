using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity.UI.V4.Pages.Account.Internal;
using Microsoft.AspNetCore.Mvc;
using Ng_Alain.Web.Models;

namespace Ng_Alain.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PassportController : ControllerBase
    {
        [HttpPost("login")]
        public JsonResult Login(LoginRequest req)
        {
            if (req.Email == "master@weilog.net" && req.Password == "password")
            {
                return new JsonResult(new LoginResponse
                {
                    Token = "123456789",
                    Username = "charles",
                    Email = req.Email,
                    Avatar = "https://ng-alain.com/assets/img/logo-color.svg"
                });
            }
            throw new Exception("无效用户");
        }
    }
}