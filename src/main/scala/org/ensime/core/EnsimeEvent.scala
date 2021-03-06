package org.ensime.core

import org.ensime.model.LineSourcePosition
import org.ensime.util.NoteList

/**
 * A asynchronous swank protocol event
 */
sealed trait EnsimeEvent

sealed trait GeneralSwankEvent extends EnsimeEvent

case class SendBackgroundMessageEvent(code: Int, detail: Option[String]) extends GeneralSwankEvent
case object AnalyzerReadyEvent extends GeneralSwankEvent
case object FullTypeCheckCompleteEvent extends GeneralSwankEvent
case object IndexerReadyEvent extends GeneralSwankEvent
case object CompilerRestartedEvent extends GeneralSwankEvent

case object ClearAllScalaNotesEvent extends GeneralSwankEvent
case class NewScalaNotesEvent(noteList: NoteList) extends GeneralSwankEvent

sealed trait DebugEvent extends EnsimeEvent
case class DebugStepEvent(threadId: Long, threadName: String, pos: LineSourcePosition) extends DebugEvent
case class DebugBreakEvent(threadId: Long, threadName: String, pos: LineSourcePosition) extends DebugEvent
case class DebugVMDeathEvent() extends DebugEvent
case class DebugVMStartEvent() extends DebugEvent
case class DebugVMDisconnectEvent() extends DebugEvent
case class DebugExceptionEvent(excId: Long, threadId: Long, threadName: String, pos: Option[LineSourcePosition]) extends DebugEvent
case class DebugThreadStartEvent(threadId: Long) extends DebugEvent
case class DebugThreadDeathEvent(threadId: Long) extends DebugEvent
case class DebugOutputEvent(out: String) extends DebugEvent

