using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WeiAdmin.Web.Models;

namespace WeiAdmin.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PassportController : ControllerApiBase
    {
        [HttpPost("login")]
        public JsonResult Login(LoginRequest req)
        {
            if (req.email == "cipchk@qq.com" && req.password == "wodemima")
            {
                return Output(new LoginResponse
                {
                    token = "123456789",
                    username = "cipchk",
                    email = req.email,
                    avatar = "https://ng-alain.com/assets/img/logo-color.svg"
                });
            }
            throw new Exception("无效用户");
        }
    }
}
