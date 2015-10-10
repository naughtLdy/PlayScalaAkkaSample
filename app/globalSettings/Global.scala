import akka.actor.Props
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import play.api.libs.concurrent.Akka
import play.api.libs.concurrent.Execution.Implicits._
import play.api.{Application, GlobalSettings}
import play.api.Play.current


object Global extends GlobalSettings {

	val system = akka.actor.ActorSystem("SampleSystem")
	val actor = system.actorOf(Props(classOf[SampleActor]))

	override def onStart(app: Application) = {
		// どのスケジュールを実行するか、どのアクターにメッセージを送信するか、どういうメッセージを送信するかを指定する
		QuartzSchedulerExtension(system).schedule("Every10Seconds", actor, "10秒")
		//QuartzSchedulerExtension(system).schedule("Every10Minutes", actor, "10分")
		//QuartzSchedulerExtension(system).schedule("9oclockAnd18oclock", actor, "9時17時")
		//QuartzSchedulerExtension(system).schedule("EndOfEachMonth", actor, "月末")
	}

	override def onStop(app: Application) = {
		system.shutdown()
	}

}