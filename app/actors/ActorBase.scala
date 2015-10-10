import akka.actor.Actor
import play.api.Logger

trait ActorBase extends Actor {

	/** Akkaから自動的にCallされる */
	def receive = {
		case message: String => {
			println(message)
			execute
		}
	}

	/** 処理本体 */
	def execute()
}