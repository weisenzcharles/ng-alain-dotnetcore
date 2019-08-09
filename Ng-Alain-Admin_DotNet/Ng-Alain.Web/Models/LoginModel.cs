using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Ng_Alain.Web.Models
{
    public class LoginModel
    {
    }

    public class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }

    public class LoginRequest
    {
        [Required(ErrorMessage = "邮箱不能为空")]
        [EmailAddress(ErrorMessage = "邮箱格式不正确")]
        public string Email { get; set; }

        [Required(ErrorMessage = "密码不能为空")]
        public string Password { get; set; }
    }

    public class LoginResponse
    {
        public string Token { get; set; }

        public string Username { get; set; }

        public string Email { get; set; }

        public string Avatar { get; set; }
    }
}
