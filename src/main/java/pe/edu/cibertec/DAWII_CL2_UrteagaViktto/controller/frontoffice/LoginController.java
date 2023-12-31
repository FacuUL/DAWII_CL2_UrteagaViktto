package pe.edu.cibertec.DAWII_CL2_UrteagaViktto.controller.frontoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWII_CL2_UrteagaViktto.model.bd.Usuario;
import pe.edu.cibertec.DAWII_CL2_UrteagaViktto.service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {

    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "frontoffice/auth/frmRegistroUsuario";
    }

    @PostMapping("/login-success")
    public String loginSucces(HttpServletRequest request){
        UserDetails usuario = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario.getUsername());
        return "frontoffice/auth/home";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.saveUser(usuario);
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "frontoffice/auth/frmlogin";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "frontoffice/auth/frmChangePassword";
    }

    @PostMapping("/change-password")
    public String updatePassword(@RequestParam("newPassword") String newPassword) {
        return "frontoffice/auth/frmlogin";
    }
}
