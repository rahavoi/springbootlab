@RestController
class HelloController {
	@RequestMapping("/")
	def hello() {
		return "Hello Boot World"
	}
}