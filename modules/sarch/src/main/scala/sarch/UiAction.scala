package sarch

class UiAction(val v: () ⇒ Unit) {
  /** map combinator */
  def map[B](f: Unit ⇒ B) = UiAction(f(v()))

  /** flatMap combinator */
  def flatMap[B](f: Unit ⇒ UiAction) = UiAction(f(v()).v())

  /** Replace the resulting value with a new one */
  def withResult[B](result: B) = UiAction {
    v(); result
  }

  /** Combine (sequence) with another UI action */
  def +[B](next: ⇒ UiAction) = UiAction {
    v(); next.v()
  }

  /** Run the action on the UI thread */
  def run = v()

}

object UiAction {

  /** A UI action that does nothing */
  def nop = UiAction(())

  /** Create a UI action */
  def apply[A](v: ⇒ A) = new UiAction(() ⇒ v)

  /** Combine (sequence) several UI actions together */
  def sequence[A](vs: UiAction*) = UiAction(vs.map(_.v()))

  /** Run a UI action on the UI thread */
  def run[A](ui: UiAction) = ui.run

  /** Run several UI actions on the UI thread */
  def run[A](ui1: UiAction, ui2: UiAction, uis: UiAction*) = sequence(ui1 +: ui2 +: uis: _*).run

}