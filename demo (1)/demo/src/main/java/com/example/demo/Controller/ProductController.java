package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping({"/", "viewProductList"})
    public String viewAllProductItems(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", service.getAllProductItems());
        model.addAttribute("message", message);

        return "ViewProductList";
    }

    @GetMapping("/updateProductStatus/{id}")
    public String updateProductStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.updateStatus(id)) {
            redirectAttributes.addFlashAttribute("message", "Update Success");
            return "redirect:/viewProductList";
        }

        redirectAttributes.addFlashAttribute("message", "Update Failure");
        return "redirect:/viewProductList";
    }

    @GetMapping("/addProductItem")
    public String addProductItem(Model model) {
        model.addAttribute("product", new Product());

        return "AddProductItem";
    }

    @PostMapping("/saveProductItem")
    public String saveProductItem(Product product, RedirectAttributes redirectAttributes) {
        if(service.saveOrUpdateProductItem(product)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewProductList";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addProductItem";
    }

    @GetMapping("/editProductItem/{id}")
    public String editProductItem(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.getProductItemById(id));

        return "EditProductItem";
    }

    @PostMapping("/editSaveProductItem")
    public String editSaveProductItem(Product product, RedirectAttributes redirectAttributes) {
        if(service.saveOrUpdateProductItem(product)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewProductList";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editProductItem/" + product.getId();
    }

    @GetMapping("/deleteProductItem/{id}")
    public String deleteProductItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.deleteProductItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/viewProductList";
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewProductList";
    }

}




// package com.example.ToDoAppwiththymeleaf.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.example.ToDoAppwiththymeleaf.model.ToDo;
// import com.example.ToDoAppwiththymeleaf.service.ToDoService;

// @Controller
// public class ToDoController {

// 	@Autowired
// 	private ToDoService service;

// 	@GetMapping({"/", "viewToDoList"})
// 	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
// 		model.addAttribute("list", service.getAllToDoItems());
// 		model.addAttribute("message", message);

// 		return "ViewToDoList";
// 	}

// 	@GetMapping("/updateToDoStatus/{id}")
// 	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
// 		if (service.updateStatus(id)) {
// 			redirectAttributes.addFlashAttribute("message", "Update Success");
// 		} else {
// 			redirectAttributes.addFlashAttribute("message", "Update Failure");
// 		}

// 		return "redirect:/viewToDoList";
// 	}

// 	@GetMapping("/addToDoItem")
// 	public String addToDoItem(Model model) {
// 		model.addAttribute("todo", new ToDo());

// 		return "AddToDoItem";
// 	}

// 	@PostMapping("/saveToDoItem")
// 	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
// 		if(service.saveOrUpdateToDoItem(todo)) {
// 			redirectAttributes.addFlashAttribute("message", "Save Success");
// 		} else {
// 			redirectAttributes.addFlashAttribute("message", "Save Failure");
// 		}

// 		return "redirect:/viewToDoList";
// 	}

// 	@GetMapping("/editToDoItem/{id}")
// 	public String editToDoItem(@PathVariable Long id, Model model) {
// 		model.addAttribute("todo", service.getToDoItemById(id));

// 		return "EditToDoItem";
// 	}

// 	@PostMapping("/editSaveToDoItem")
// 	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
// 		if(service.saveOrUpdateToDoItem(todo)) {
// 			redirectAttributes.addFlashAttribute("message", "Edit Success");
// 		} else {
// 			redirectAttributes.addFlashAttribute("message", "Edit Failure");
// 		}

// 		return "redirect:/viewToDoList";
// 	}

// 	@GetMapping("/deleteToDoItem/{id}")
// 	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
// 		if (service.deleteToDoItem(id)) {
// 			redirectAttributes.addFlashAttribute("message", "Delete Success");
// 		} else {
// 			redirectAttributes.addFlashAttribute("message", "Delete Failure");
// 		}

// 		return "redirect:/viewToDoList";
// 	}

// }