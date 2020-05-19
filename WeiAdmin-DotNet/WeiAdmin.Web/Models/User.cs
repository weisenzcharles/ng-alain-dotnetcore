using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WeiAdmin.Web.Models
{
    public class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }

    public class LoginRequest
    {
        [Required(ErrorMessage = "邮箱不能为空")]
        [EmailAddress(ErrorMessage = "邮箱格式不正确")]
        public string email { get; set; }

        [Required(ErrorMessage = "密码不能为空")]
        public string password { get; set; }
    }

    public class LoginResponse
    {
        public string token { get; set; }

        public string username { get; set; }

        public string email { get; set; }

        public string avatar { get; set; }
    }
}
