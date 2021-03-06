(ns zetawar.data)

(def dicts
  {:en
   {;; General
    :save-button "Save"
    :cancel-button "Cancel"
    :close-button "Close"

    ;; Game links
    :copy-game-url-link "Copy Link"
    :new-game-link "New Game"
    :end-turn-link "End Turn?"

    ;; Game labels
    :credits-label "Credits"
    :round-label "Round"

    ;; Faction configuration
    :configure-faction-tip "Configure faction"
    :configure-faction-title-prefix "Configure faction: "
    :player-type-label "Player type"

    ;; Faction colors
    :red-name "Red"
    :blue-name "Blue"
    :yellow-name "Yellow"
    :pink-name "Pink"
    :green-name "Green"
    :orange-name "Orange"

    ;; Armor types
    :personnel-name "Personnel"
    :armored-name "Armored"
    :naval-name "Naval"
    :air-name "Air"

    ;; New game settings
    :new-game-title "Start a new game"
    :scenario-label "Scenario"
    :start-button "Start"

    ;; Unit and base actions
    :move-unit-button "Move"
    :build-unit-button "Build"
    :attack-unit-button "Attack"
    :repair-unit-button "Repair"
    :field-repair-button "Field Repair"
    :capture-base-button "Capture"

    ;; Building units
    :build-title "Select a unit to build"
    :unit-cost-label "Cost: "
    :armor-type-label "Armor Type"
    :movement-label "Move- ment"
    :armor-label "Armor"
    :range-label "Range"
    :attack-label "Attack"
    :field-repair-label "Field Repair?"
    :while-capturing-label "While capturing: "
    :unit-cannot-capture-bases-label "Unit cannot capture bases"

    ;; Win dialog
    :win-title "Congratulations! You won!"
    :win-body
    (str "Thanks for playing Zetawar! If you're interested in staying up-to-date "
         "with Zetawar as it develops, please follow "
         "<a href=\"https://twitter.com/ZetawarGame\">ZetawarGame</a> on Twitter.")

    ;; Gameplay tips
    :select-unit-or-base-tip "Select a unit or base."
    :select-target-or-destination-tip "Select a destination or target to move, attack, or repair."
    :multiplayer-tip
    (str "To play multiplayer follow the instructions "
         "<a href=\"https://www.kickstarter.com/projects/311016908/zetawar/posts/1608417\">"
         "here</a>.")

    ;; Status information
    :tile-coordinates-label "X,Y (Tile Coordinates)"
    :terrain-effects-label "Movement Cost, Attack Bonus, Armor Bonus"
    :movement-cost-label "Movement Cost"
    :attack-bonus-label "Attack Bonus"
    :armor-bonus-label "Armor Bonus"
    :selected-label "Selected: "
    :targeted-label "Targeted: "
    :hover-tile-location "Hover: "

    ;; Ending turn
    :end-turn-alert "Are you sure you want to end your turn? You still have available moves."
    :end-turn-confirm "Yes, end my turn"}})


;; TODO: remove redundant id keys (?)
(def rulesets
  {:zetawar
   {:settings
    {:ranged-attack-bonus   1
     :adjacent-attack-bonus 1
     :flanking-attack-bonus 2
     :opposite-attack-bonus 3
     :stochastic-damage     false
     :self-repair           false
     :move-through-friendly true}

    :terrains
    {:plains        {:description "Plains"
                     :image "tilesets/elite-command/terrains/plains.png"}
     :mountains     {:description "Mountains"
                     :image "tilesets/elite-command/terrains/mountains.png"}
     :woods         {:description "Woods"
                     :image "tilesets/elite-command/terrains/woods.png"}
     :desert        {:description "Desert"
                     :image "tilesets/elite-command/terrains/desert.png"}
     :tundra        {:description "Tundra"
                     :image "tilesets/elite-command/terrains/tundra.png"}
     :swamp         {:description "Swamp"
                     :image "tilesets/elite-command/terrains/swamp.png"}
     :ford          {:description "Ford"
                     :image "tilesets/elite-command/terrains/ford.png"}
     :shallow-water {:description "Shallow Water"
                     :image "tilesets/elite-command/terrains/shallow-water.png"}
     :deep-water    {:description "Deep Water"
                     :image "tilesets/elite-command/terrains/deep-water.png"}
     :base          {:description "Base"
                     :image "tilesets/elite-command/terrains/base-COLOR.png"}
     :seaport       {:description "Seaport"
                     :image "tilesets/elite-command/terrains/seaport-COLOR.png"}
     :airfield      {:description "Airfield"
                     :image "tilesets/elite-command/terrains/airfield-COLOR.png"}}

    :unit-state-maps
    {:move-attack-once
     {:start-state :start
      :built-state :done
      :states
      {:start {:transitions
               {:move-unit         :moved
                :attack-unit       :done
                :repair-unit       :done
                :field-repair-unit :done
                :capture-base      :done}}
       :moved {:transitions
               {:attack-unit       :done
                :repair-unit       :done
                :field-repair-unit :done
                :capture-base      :done}}
       :done  {}}}

     :move-attack-twice
     {:start-state :start
      :built-state :done
      :states
      {:start              {:transitions
                            {:move-unit    :moved-1-attacked-0
                             :attack-unit  :moved-0-attacked-1
                             :repair-unit  :done
                             :capture-base :done}}
       :moved-0-attacked-1 {:transitions
                            {:attack-unit  :done
                             :capture-base :done}}
       :moved-1-attacked-0 {:transitions
                            {:attack-unit  :moved-1-attacked-1
                             :capture-base :done}}
       :moved-1-attacked-1 {:transitions
                            {:attack-unit  :done
                             :capture-base :done}}
       :done               {}}}

     :free-attack-once
     {:start-state :start
      :built-state :done
      :states
      {:start              {:transitions
                            {:move-unit    :moved-1-attacked-0
                             :attack-unit  :moved-0-attacked-1
                             :repair-unit  :done
                             :capture-base :done}}
       :moved-0-attacked-1 {:transitions
                            {:move-unit    :done
                             :capture-base :done}}
       :moved-1-attacked-0 {:transitions
                            {:attack-unit  :done
                             :capture-base :done}}
       :done               {}}}

     :free-attack-twice
     {:start-state :start
      :built-state :done
      :states
      {:start              {:transitions
                            {:move-unit    :moved-1-attacked-0
                             :attack-unit  :moved-0-attacked-1
                             :repair-unit  :done
                             :capture-base :done}}
       :moved-0-attacked-1 {:transitions
                            {:move-unit    :moved-1-attacked-1
                             :attack-unit  :moved-0-attacked-2
                             :repair-unit  :done
                             :capture-base :done}}
       :moved-0-attacked-2 {:transitions
                            {:move-unit    :done
                             :repair-unit  :done
                             :capture-base :done}}
       :moved-1-attacked-0 {:transitions
                            {:attack-unit  :moved-1-attacked-1
                             :repair-unit  :done
                             :capture-base :done}}
       :moved-1-attacked-1 {:transitions
                            {:attack-unit  :done
                             :repair-unit  :done
                             :capture-base :done}}
       :done               {}}}

     :exclusive
     {:start-state :start
      :built-state :done
      :states
      {:start {:transitions
               {:move-unit   :done
                :attack-unit :done
                :repair-unit :done}}
       :done  {}}}}

    ;; TODO: check how repair amount works in Elite Command
    ;; TODO: add :buildable-at (or -by?) => {<terrain type ids>...}
    :units
    {:infantry {:description "Infantry"
                :cost 75
                :movement 9
                :can-capture true
                :can-repair #{}
                :buildable-at #{:base}
                :min-range 1
                :max-range 1
                :armor-type :personnel
                :armor 6
                :capturing-armor 4
                :repair 1
                :state-map :move-attack-once
                :image "tilesets/elite-command/units/infantry-COLOR.png"
                :terrain-effects
                {:plains    {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
                 :mountains {:movement-cost 6 :armor-bonus  5 :attack-bonus  2}
                 :woods     {:movement-cost 4 :armor-bonus  3 :attack-bonus  2}
                 :desert    {:movement-cost 4 :armor-bonus -1 :attack-bonus -1}
                 :tundra    {:movement-cost 4 :armor-bonus -1 :attack-bonus -1}
                 :swamp     {:movement-cost 6 :armor-bonus -2 :attack-bonus -2}
                 :ford      {:movement-cost 5 :armor-bonus -1 :attack-bonus -1}
                 :base      {:movement-cost 2 :armor-bonus  3 :attack-bonus  2}
                 :seaport   {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                 :airfield  {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}}
                :attack-strengths
                {:personnel 6
                 :armored   3
                 :naval     4
                 :air       0}
                :zoc
                [:personnel :armored]}
     :grenadier {:description "Grenadier"
                 :cost 150
                 :movement 9
                 :can-capture true
                 :can-repair #{}
                 :buildable-at #{:base}
                 :min-range 1
                 :max-range 2
                 :armor-type :personnel
                 :armor 8
                 :capturing-armor 6
                 :repair 1
                 :state-map :move-attack-once
                 :image "tilesets/elite-command/units/grenadier-COLOR.png"
                 :terrain-effects
                 {:plains    {:movement-cost 4 :armor-bonus  0 :attack-bonus  0}
                  :mountains {:movement-cost 9 :armor-bonus  5 :attack-bonus  2}
                  :woods     {:movement-cost 4 :armor-bonus  3 :attack-bonus -1}
                  :desert    {:movement-cost 5 :armor-bonus -1 :attack-bonus  0}
                  :tundra    {:movement-cost 5 :armor-bonus -1 :attack-bonus  0}
                  :swamp     {:movement-cost 9 :armor-bonus -2 :attack-bonus -2}
                  :ford      {:movement-cost 9 :armor-bonus -1 :attack-bonus -1}
                  :base      {:movement-cost 3 :armor-bonus  3 :attack-bonus -1}
                  :seaport   {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
                  :airfield  {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}}
                 :attack-strengths
                 {:personnel 8
                  :armored   9
                  :naval     6
                  :air       3}
                 :zoc
                 [:personnel :armored :naval]}
     :mortar {:description "Mortar"
              :cost 200
              :movement 9
              :can-capture true
              :can-repair #{}
              :buildable-at #{:base}
              :min-range 2
              :max-range 3
              :armor-type :personnel
              :armor 6
              :capturing-armor 4
              :repair 1
              :state-map :move-attack-once
              :image "tilesets/elite-command/units/mortar-COLOR.png"
              :terrain-effects
              {:plains    {:movement-cost 4 :armor-bonus  0 :attack-bonus  0}
               :mountains {:movement-cost 9 :armor-bonus  5 :attack-bonus  3}
               :woods     {:movement-cost 4 :armor-bonus  3 :attack-bonus -2}
               :desert    {:movement-cost 5 :armor-bonus -1 :attack-bonus  0}
               :tundra    {:movement-cost 5 :armor-bonus -1 :attack-bonus -1}
               :swamp     {:movement-cost 9 :armor-bonus -2 :attack-bonus -2}
               :ford      {:movement-cost 9 :armor-bonus -1 :attack-bonus -2}
               :base      {:movement-cost 3 :armor-bonus  3 :attack-bonus -2}
               :seaport   {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
               :airfield  {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}}
              :attack-strengths
              {:personnel 10
               :armored   10
               :naval     10
               :air       0}}
     :ranger {:description "Ranger"
              :cost 200
              :movement 9
              :can-capture true
              :can-repair #{}
              :buildable-at #{:base}
              :min-range 1
              :max-range 1
              :armor-type :personnel
              :armor 9
              :capturing-armor 7
              :repair 1
              :state-map :move-attack-once
              :image "tilesets/elite-command/units/ranger-COLOR.png"
              :terrain-effects
              {:plains        {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
               :mountains     {:movement-cost 6 :armor-bonus  5 :attack-bonus  2}
               :woods         {:movement-cost 3 :armor-bonus  4 :attack-bonus  2}
               :desert        {:movement-cost 3 :armor-bonus -1 :attack-bonus -1}
               :tundra        {:movement-cost 3 :armor-bonus -1 :attack-bonus -1}
               :swamp         {:movement-cost 3 :armor-bonus -2 :attack-bonus -2}
               :ford          {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
               :shallow-water {:movement-cost 6 :armor-bonus -2 :attack-bonus -2}
               :base          {:movement-cost 2 :armor-bonus  3 :attack-bonus  2}
               :seaport       {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
               :airfield      {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}}
              :attack-strengths
              {:personnel 9
               :armored   4
               :naval     6
               :air       0}
              :zoc
              [:personnel :armored]}
     :sniper {:description "Sniper"
              :cost 275
              :movement 9
              :can-capture true
              :can-repair #{}
              :buildable-at #{:base}
              :min-range 2
              :max-range 4
              :armor-type :personnel
              :armor 5
              :capturing-armor 3
              :repair 1
              :state-map :move-attack-once
              :image "tilesets/elite-command/units/sniper-COLOR.png"
              :terrain-effects
              {:plains    {:movement-cost 4 :armor-bonus  0 :attack-bonus  0}
               :mountains {:movement-cost 9 :armor-bonus  5 :attack-bonus  4}
               :woods     {:movement-cost 4 :armor-bonus  3 :attack-bonus -4}
               :desert    {:movement-cost 5 :armor-bonus -1 :attack-bonus -1}
               :tundra    {:movement-cost 5 :armor-bonus -1 :attack-bonus -1}
               :swamp     {:movement-cost 9 :armor-bonus -2 :attack-bonus -3}
               :ford      {:movement-cost 9 :armor-bonus -1 :attack-bonus -6}
               :base      {:movement-cost 3 :armor-bonus  3 :attack-bonus  2}
               :seaport   {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}}
              :attack-strengths
              {:personnel 8
               :armored   0
               :naval     0
               :air       0}}
     :medic {:description "Medic"
             :cost 100
             :movement 9
             :can-capture true
             :can-repair #{:personnel}
             :buildable-at #{:base}
             :min-range 1
             :max-range 1
             :armor-type :personnel
             :armor 6
             :capturing-armor 4
             :repair 1
             :state-map :move-attack-once
             :image "tilesets/elite-command/units/medic-COLOR.png"
             :terrain-effects
             {:plains    {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
              :mountains {:movement-cost 6 :armor-bonus  5 :attack-bonus  2}
              :woods     {:movement-cost 4 :armor-bonus  3 :attack-bonus  2}
              :desert    {:movement-cost 4 :armor-bonus -1 :attack-bonus -1}
              :tundra    {:movement-cost 4 :armor-bonus -1 :attack-bonus -1}
              :swamp     {:movement-cost 6 :armor-bonus -2 :attack-bonus -2}
              :ford      {:movement-cost 5 :armor-bonus -1 :attack-bonus -1}
              :base      {:movement-cost 2 :armor-bonus  3 :attack-bonus  2}
              :seaport   {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
              :airfield  {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}}
            :attack-strengths
            {:personnel 5
             :armored   2
             :naval     3
             :air       0}
            :zoc
            [:personnel :armored]}
    :engineer {:description "Engineer"
               :cost 200
               :movement 9
               :can-capture true
               :can-repair #{:armored}
               :buildable-at #{:base}
               :min-range 1
               :max-range 1
               :armor-type :personnel
               :armor 6
               :capturing-armor 4
               :repair 1
               :state-map :move-attack-once
               :image "tilesets/elite-command/units/engineer-COLOR.png"
               :terrain-effects
               {:plains        {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
                :mountains     {:movement-cost 6 :armor-bonus  5 :attack-bonus  2}
                :woods         {:movement-cost 4 :armor-bonus  3 :attack-bonus  2}
                :desert        {:movement-cost 4 :armor-bonus -1 :attack-bonus -1}
                :tundra        {:movement-cost 4 :armor-bonus -1 :attack-bonus -1}
                :swamp         {:movement-cost 6 :armor-bonus -2 :attack-bonus -2}
                :ford          {:movement-cost 5 :armor-bonus -1 :attack-bonus -1}
                :shallow-water {:movement-cost 7 :armor-bonus -2 :attack-bonus -2}
                :base          {:movement-cost 2 :armor-bonus  3 :attack-bonus  2}
                :seaport       {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                :airfield      {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}}
              :attack-strengths
              {:personnel 5
               :armored   2
               :naval     3
               :air       0}
              :zoc
              [:personnel :armored]}
     ;; Armored
     :humvee {:description "Humvee"
              :cost 300
              :movement 15
              :can-capture false
              :can-repair #{}
              :buildable-at #{:base}
              :min-range 1
              :max-range 1
              :armor-type :armored
              :armor 8
              :repair 1
              :state-map :free-attack-twice
              :image "tilesets/elite-command/units/humvee-COLOR.png"
              :terrain-effects
              {:plains   {:movement-cost 3  :armor-bonus  0 :attack-bonus  0}
               :woods    {:movement-cost 6  :armor-bonus -2 :attack-bonus -2}
               :desert   {:movement-cost 3  :armor-bonus  0 :attack-bonus  0}
               :tundra   {:movement-cost 6  :armor-bonus  0 :attack-bonus  0}
               :swamp    {:movement-cost 12 :armor-bonus -3 :attack-bonus -3}
               :ford     {:movement-cost 12 :armor-bonus -1 :attack-bonus -1}
               :base     {:movement-cost 2  :armor-bonus  0 :attack-bonus  0}
               :seaport  {:movement-cost 2  :armor-bonus  0 :attack-bonus  0}
               :airfield {:movement-cost 2  :armor-bonus  0 :attack-bonus  0}}
              :attack-strengths
              {:personnel 9
               :armored   3
               :naval     6
               :air       0}
              :zoc
              [:personnel :armored]}
     :tank {:description "Tank"
            :cost 350
            :movement 12
            :can-capture false
            :can-repair #{}
            :buildable-at #{:base}
            :min-range 1
            :max-range 1
            :armor-type :armored
            :armor 12
            :repair 1
            :state-map :move-attack-once
            :image "tilesets/elite-command/units/tank-COLOR.png"
            :terrain-effects
            {:plains   {:movement-cost 3 :armor-bonus  0 :attack-bonus 0}
             :woods    {:movement-cost 6 :armor-bonus -3 :attack-bonus 0}
             :desert   {:movement-cost 4 :armor-bonus  0 :attack-bonus 0}
             :tundra   {:movement-cost 5 :armor-bonus  0 :attack-bonus 0}
             :swamp    {:movement-cost 8 :armor-bonus -4 :attack-bonus 0}
             :ford     {:movement-cost 8 :armor-bonus  0 :attack-bonus 0}
             :base     {:movement-cost 2 :armor-bonus -2 :attack-bonus 0}
             :seaport  {:movement-cost 2 :armor-bonus  0 :attack-bonus 0}
             :airfield {:movement-cost 2 :armor-bonus  0 :attack-bonus 0}}
            :attack-strengths
            {:personnel 10
             :armored   10
             :naval     9
             :air       0}
            :zoc
            [:personnel :armored]}
     :mobile-flak {:description "Mobile Flak"
                   :cost 350
                   :movement 9
                   :can-capture false
                   :can-repair #{}
                   :min-range 1
                   :max-range 3
                   :armor-type :armored
                   :armor 8
                   :repair 1
                   :state-map :move-attack-twice
                   :buildable-at #{:base :airfield}
                   :image "tilesets/elite-command/units/mobileflak-COLOR.png"
                   :terrain-effects
                   {:plains   {:movement-cost 3 :armor-bonus  0 :attack-bonus  0}
                    :woods    {:movement-cost 6 :armor-bonus -2 :attack-bonus -3}
                    :desert   {:movement-cost 6 :armor-bonus  0 :attack-bonus  0}
                    :tundra   {:movement-cost 6 :armor-bonus -1 :attack-bonus  0}
                    :swamp    {:movement-cost 6 :armor-bonus -3 :attack-bonus -2}
                    :ford     {:movement-cost 6 :armor-bonus -2 :attack-bonus -1}
                    :base     {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                    :seaport  {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                    :airfield {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}}
                   :attack-strengths
                   {:personnel 0
                    :armored   0
                    :naval     0
                    :air       14}
                   :zoc
                   [:air]}
     :heavy-tank {:description "Heavy Tank"
                  :cost 500
                  :movement 12
                  :can-capture false
                  :can-repair #{}
                  :buildable-at #{:base}
                  :min-range 1
                  :max-range 1
                  :armor-type :armored
                  :armor 15
                  :repair 1
                  :state-map :move-attack-once
                  :image "tilesets/elite-command/units/heavytank-COLOR.png"
                  :terrain-effects
                  {:plains  {:movement-cost 4 :armor-bonus  0 :attack-bonus 0}
                   :woods   {:movement-cost 6 :armor-bonus -3 :attack-bonus 0}
                   :desert  {:movement-cost 5 :armor-bonus  0 :attack-bonus 0}
                   :tundra  {:movement-cost 7 :armor-bonus  0 :attack-bonus 0}
                   :swamp   {:movement-cost 8 :armor-bonus -4 :attack-bonus 0}
                   :ford    {:movement-cost 8 :armor-bonus -2 :attack-bonus 0}
                   :base    {:movement-cost 3 :armor-bonus -2 :attack-bonus 0}
                   :seaport {:movement-cost 3 :armor-bonus  0 :attack-bonus 0}}
                  :attack-strengths
                  {:personnel 15
                   :armored   12
                   :naval     14
                   :air       0}
                  :zoc
                  [:personnel :armored]}
     :artillery {:description "Artillery"
                 :cost 400
                 :movement 8
                 :can-capture false
                 :can-repair #{}
                 :buildable-at #{:base}
                 :min-range 3
                 :max-range 4
                 :armor-type :armored
                 :armor 6
                 :repair 1
                 :state-map :exclusive
                 :image "tilesets/elite-command/units/artillery-COLOR.png"
                 :terrain-effects
                 {:plains  {:movement-cost 4 :armor-bonus  0 :attack-bonus  0}
                  :woods   {:movement-cost 6 :armor-bonus -3 :attack-bonus -3}
                  :desert  {:movement-cost 5 :armor-bonus  0 :attack-bonus  0}
                  :tundra  {:movement-cost 5 :armor-bonus  0 :attack-bonus  0}
                  :swamp   {:movement-cost 6 :armor-bonus -3 :attack-bonus -3}
                  :ford    {:movement-cost 6 :armor-bonus -2 :attack-bonus  0}
                  :base    {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                  :seaport {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}}
                 :attack-strengths
                 {:personnel 12
                  :armored   13
                  :naval     14
                  :air       0}}
     :heavy-artillery {:description "Heavy Artillery"
                       :cost 1250
                       :movement 2
                       :can-capture false
                       :can-repair #{}
                       :buildable-at #{:base}
                       :min-range 4
                       :max-range 6
                       :armor-type :armored
                       :armor 8
                       :repair 1
                       :state-map :exclusive
                       :image "tilesets/elite-command/units/heavyartillery-COLOR.png"
                       :terrain-effects
                       {:plains  {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                        :woods   {:movement-cost 2 :armor-bonus -3 :attack-bonus -5}
                        :desert  {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                        :tundra  {:movement-cost 2 :armor-bonus  0 :attack-bonus  0}
                        :swamp   {:movement-cost 2 :armor-bonus -3 :attack-bonus -5}
                        :ford    {:movement-cost 2 :armor-bonus -2 :attack-bonus  0}
                        :base    {:movement-cost 1 :armor-bonus  0 :attack-bonus  0}
                        :seaport {:movement-cost 1 :armor-bonus  0 :attack-bonus  0}}
                       :attack-strengths
                       {:personnel 14
                        :armored   15
                        :naval     15
                        :air       0}}
     ;; Naval
     :frigate {:description "Frigate"
               :cost 300
               :movement 8
               :can-capture false
               :can-repair #{}
               :buildable-at #{:seaport}
               :min-range 1
               :max-range 1
               :armor-type :naval
               :armor 8
               :repair 1
               :state-map :free-attack-twice
               :image "tilesets/elite-command/units/frigate-COLOR.png"
               :terrain-effects
               {:deep-water    {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}
                :shallow-water {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}
                :seaport       {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}}
               :attack-strengths
               {:personnel 9
                :armored   9
                :naval     8
                :air       0}
               :zoc
               [:personnel :armored]}
     :destroyer {:description "Destroyer"
                 :cost 600
                 :movement 5
                 :can-capture false
                 :can-repair #{}
                 :buildable-at #{:seaport}
                 :min-range 1
                 :max-range 4
                 :armor-type :naval
                 :armor 12
                 :repair 1
                 :state-map :free-attack-once
                 :image "tilesets/elite-command/units/destroyer-COLOR.png"
                 :terrain-effects
                 {:deep-water {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}
                  :seaport    {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}}
                 :attack-strengths
                 {:personnel 10
                  :armored   12
                  :naval     8
                  :air       0}
                 :zoc
                 [:personnel :armored]}
     :cruiser {:description "Cruiser"
               :cost 1000
               :movement 4
               :can-capture false
               :can-repair #{}
               :buildable-at #{:seaport}
               :min-range 1
               :max-range 3
               :armor-type :naval
               :armor 15
               :repair 1
               :state-map :move-attack-twice
               :image "tilesets/elite-command/units/cruiser-COLOR.png"
               :terrain-effects
               {:deep-water {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}
                :seaport    {:movement-cost 1 :armor-bonus 0 :attack-bonus 0}}
               :attack-strengths
               {:personnel 14
                :armored   16
                :naval     12
                :air       14}
               :zoc
               [:personnel :armored]}
     ;; Air
     :fighter {:description "Fighter"
               :cost 450
               :movement 24
               :can-capture false
               :can-repair #{}
               :min-range 1
               :max-range 1
               :armor-type :air
               :armor 12
               :repair 1
               :state-map :free-attack-once
               :buildable-at #{:airfield}
               :image "tilesets/elite-command/units/fighter-COLOR.png"
               :terrain-effects
               {:plains        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :mountains     {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :woods         {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :desert        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :tundra        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :swamp         {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :ford          {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :deep-water    {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :shallow-water {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :base          {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :seaport       {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :airfield      {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}}
               :attack-strengths
               {:personnel 4
                :armored   6
                :naval     8
                :air       16}}
     :bomber {:description "Bomber"
              :cost 650
              :movement 24
              :can-capture false
              :can-repair #{}
              :min-range 1
              :max-range 1
              :armor-type :air
              :armor 10
              :repair 1
              :state-map :free-attack-once
              :buildable-at #{:airfield}
              :image "tilesets/elite-command/units/bomber-COLOR.png"
              :terrain-effects
              {:plains        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :mountains     {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :woods         {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :desert        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :tundra        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :swamp         {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :ford          {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :deep-water    {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :shallow-water {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :base          {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :seaport       {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
               :airfield      {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}}
              :attack-strengths
              {:personnel 14
               :armored   16
               :naval     15
               :air       4}}
     :gunship {:description "Gunship"
               :cost 350
               :movement 15
               :can-capture false
               :can-repair #{}
               :min-range 1
               :max-range 1
               :armor-type :air
               :armor 8
               :repair 1
               :state-map :free-attack-once
               :buildable-at #{:airfield}
               :image "tilesets/elite-command/units/gunship-COLOR.png"
               :terrain-effects
               {:plains        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :mountains     {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :woods         {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :desert        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :tundra        {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :swamp         {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :ford          {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :deep-water    {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :shallow-water {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :base          {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :seaport       {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}
                :airfield      {:movement-cost 3 :armor-bonus 0 :attack-bonus 0}}
               :attack-strengths
               {:personnel 18
                :armored   10
                :naval     9
                :air       8}
               :zoc
               [:personnel :armored :naval]}}}})

;; TODO: remove redundant id keys (?)
(def maps
  {:sterlings-aruba
   {:id :sterlings-aruba
    :description "Sterling's Aruba"
    :created-by "Weewar"
    :terrains
    [;; Row 1
     {:q 1 :r 0 :terrain-type :plains}
     {:q 2 :r 0 :terrain-type :plains}
     {:q 3 :r 0 :terrain-type :mountains}
     {:q 4 :r 0 :terrain-type :deep-water}
     {:q 5 :r 0 :terrain-type :deep-water}
     {:q 6 :r 0 :terrain-type :deep-water}
     ;; Row 2
     {:q 0 :r 1 :terrain-type :plains}
     {:q 1 :r 1 :terrain-type :mountains}
     {:q 2 :r 1 :terrain-type :plains} ;; Multiplayer base location
     {:q 3 :r 1 :terrain-type :plains}
     {:q 4 :r 1 :terrain-type :mountains}
     {:q 5 :r 1 :terrain-type :woods}
     {:q 6 :r 1 :terrain-type :deep-water}
     {:q 7 :r 1 :terrain-type :deep-water}
     ;; Row 3
     {:q 0 :r 2 :terrain-type :mountains}
     {:q 1 :r 2 :terrain-type :plains} ;; Multiplayer base location
     {:q 2 :r 2 :terrain-type :plains}
     {:q 3 :r 2 :terrain-type :plains}
     {:q 4 :r 2 :terrain-type :plains}
     {:q 5 :r 2 :terrain-type :woods}
     {:q 6 :r 2 :terrain-type :mountains}
     {:q 7 :r 2 :terrain-type :mountains}
     {:q 8 :r 2 :terrain-type :deep-water}
     ;; Row 4
     {:q 0 :r 3 :terrain-type :plains}
     {:q 1 :r 3 :terrain-type :plains}
     {:q 2 :r 3 :terrain-type :plains}
     {:q 3 :r 3 :terrain-type :plains}
     {:q 4 :r 3 :terrain-type :woods}
     {:q 5 :r 3 :terrain-type :plains}
     {:q 6 :r 3 :terrain-type :plains}
     {:q 7 :r 3 :terrain-type :mountains}
     {:q 8 :r 3 :terrain-type :deep-water}
     ;; Row 5
     {:q 0 :r 4 :terrain-type :deep-water}
     {:q 1 :r 4 :terrain-type :mountains} ;; Multiplayer base location
     {:q 2 :r 4 :terrain-type :plains}
     {:q 3 :r 4 :terrain-type :plains}
     {:q 4 :r 4 :terrain-type :plains}
     {:q 5 :r 4 :terrain-type :plains}
     {:q 6 :r 4 :terrain-type :plains}
     {:q 7 :r 4 :terrain-type :plains}
     {:q 8 :r 4 :terrain-type :deep-water}
     ;; Row 6
     {:q 0 :r 5 :terrain-type :deep-water}
     {:q 1 :r 5 :terrain-type :plains}
     {:q 2 :r 5 :terrain-type :plains}
     {:q 3 :r 5 :terrain-type :woods}
     {:q 4 :r 5 :terrain-type :plains}
     {:q 5 :r 5 :terrain-type :plains}
     {:q 6 :r 5 :terrain-type :plains}
     {:q 7 :r 5 :terrain-type :plains}
     {:q 8 :r 5 :terrain-type :deep-water}
     ;; Row 7
     {:q 0 :r 6 :terrain-type :deep-water}
     {:q 1 :r 6 :terrain-type :mountains}
     {:q 2 :r 6 :terrain-type :plains}
     {:q 3 :r 6 :terrain-type :woods}
     {:q 4 :r 6 :terrain-type :plains}
     {:q 5 :r 6 :terrain-type :plains}
     {:q 6 :r 6 :terrain-type :plains}
     {:q 7 :r 6 :terrain-type :plains} ;; Multiplayer base location
     {:q 8 :r 6 :terrain-type :mountains}
     ;; Row 8
     {:q 0 :r 7 :terrain-type :deep-water}
     {:q 1 :r 7 :terrain-type :mountains}
     {:q 2 :r 7 :terrain-type :woods}
     {:q 3 :r 7 :terrain-type :mountains}
     {:q 4 :r 7 :terrain-type :plains}
     {:q 5 :r 7 :terrain-type :plains} ;; Multiplayer base location
     {:q 6 :r 7 :terrain-type :mountains}
     {:q 7 :r 7 :terrain-type :plains}
     ;; Row 9
     {:q 1 :r 8 :terrain-type :deep-water}
     {:q 2 :r 8 :terrain-type :deep-water}
     {:q 3 :r 8 :terrain-type :deep-water}
     {:q 4 :r 8 :terrain-type :deep-water}
     {:q 5 :r 8 :terrain-type :mountains} ;; Multiplayer base location
     {:q 6 :r 8 :terrain-type :plains}
     {:q 7 :r 8 :terrain-type :plains}]}

   :city-sprawl
   {:id :city-sprawl
    :description "City Sprawl"
    :created-by "Weewar"
    :terrains
    [;; Row 1
     {:q 2 :r 0 :terrain-type :woods}
     {:q 3 :r 0 :terrain-type :woods}
     {:q 4 :r 0 :terrain-type :woods}
     {:q 5 :r 0 :terrain-type :woods}
     {:q 6 :r 0 :terrain-type :woods}
     {:q 10 :r 0 :terrain-type :woods}
     {:q 11 :r 0 :terrain-type :woods}
     {:q 12 :r 0 :terrain-type :woods}
     ;; Row 2
     {:q 1 :r 1 :terrain-type :woods}
     {:q 2 :r 1 :terrain-type :plains}
     {:q 3 :r 1 :terrain-type :plains}
     {:q 4 :r 1 :terrain-type :plains}
     {:q 5 :r 1 :terrain-type :plains}
     {:q 6 :r 1 :terrain-type :woods}
     {:q 8 :r 1 :terrain-type :woods}
     {:q 9 :r 1 :terrain-type :plains}
     {:q 10 :r 1 :terrain-type :plains}
     {:q 11 :r 1 :terrain-type :plains}
     {:q 12 :r 1 :terrain-type :woods}
     {:q 13 :r 1 :terrain-type :woods}
     ;; Row 3
     {:q 1 :r 2 :terrain-type :woods}
     {:q 2 :r 2 :terrain-type :plains}
     {:q 3 :r 2 :terrain-type :mountains}
     {:q 4 :r 2 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 5 :r 2 :terrain-type :plains}
     {:q 6 :r 2 :terrain-type :plains}
     {:q 7 :r 2 :terrain-type :woods}
     {:q 8 :r 2 :terrain-type :woods}
     {:q 9 :r 2 :terrain-type :plains}
     {:q 10 :r 2 :terrain-type :plains}
     {:q 11 :r 2 :terrain-type :plains}
     {:q 12 :r 2 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 13 :r 2 :terrain-type :plains}
     {:q 14 :r 2 :terrain-type :woods}
     ;; Row 4
     {:q 0 :r 3 :terrain-type :plains}
     {:q 1 :r 3 :terrain-type :plains}
     {:q 2 :r 3 :terrain-type :mountains}
     {:q 3 :r 3 :terrain-type :plains} ;; Multiplayer base location (red owned)
     {:q 4 :r 3 :terrain-type :mountains}
     {:q 5 :r 3 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 6 :r 3 :terrain-type :woods}
     {:q 7 :r 3 :terrain-type :mountains}
     {:q 8 :r 3 :terrain-type :mountains}
     {:q 9 :r 3 :terrain-type :plains}
     {:q 10 :r 3 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 11 :r 3 :terrain-type :desert}
     {:q 12 :r 3 :terrain-type :woods}
     {:q 13 :r 3 :terrain-type :plains}
     {:q 14 :r 3 :terrain-type :woods}
     ;; Row 5
     {:q 0 :r 4 :terrain-type :woods}
     {:q 1 :r 4 :terrain-type :plains}
     {:q 2 :r 4 :terrain-type :plains} ;; Multiplayer base location (red)
     {:q 3 :r 4 :terrain-type :desert}
     {:q 4 :r 4 :terrain-type :plains} ;; Multiplayer base location (unknown)
     {:q 5 :r 4 :terrain-type :desert}
     {:q 6 :r 4 :terrain-type :plains}
     {:q 7 :r 4 :terrain-type :woods}
     {:q 8 :r 4 :terrain-type :mountains}
     {:q 9 :r 4 :terrain-type :plains}
     {:q 10 :r 4 :terrain-type :mountains}
     {:q 11 :r 4 :terrain-type :desert}
     {:q 12 :r 4 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 13 :r 4 :terrain-type :mountains}
     {:q 14 :r 4 :terrain-type :plains}
     ;; Row 6
     {:q 0 :r 5 :terrain-type :woods}
     {:q 1 :r 5 :terrain-type :plains}
     {:q 2 :r 5 :terrain-type :plains}
     {:q 3 :r 5 :terrain-type :plains}
     {:q 4 :r 5 :terrain-type :woods}
     {:q 5 :r 5 :terrain-type :plains}
     {:q 6 :r 5 :terrain-type :plains}
     {:q 7 :r 5 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 8 :r 5 :terrain-type :plains}
     {:q 9 :r 5 :terrain-type :plains}
     {:q 10 :r 5 :terrain-type :plains}
     {:q 11 :r 5 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 12 :r 5 :terrain-type :desert}
     {:q 13 :r 5 :terrain-type :plains} ;; Multiplayer base location (blue)
     {:q 14 :r 5 :terrain-type :woods}
     ;; Row 7
     {:q 1 :r 6 :terrain-type :woods}
     {:q 2 :r 6 :terrain-type :woods}
     {:q 3 :r 6 :terrain-type :plains}
     {:q 4 :r 6 :terrain-type :woods}
     {:q 5 :r 6 :terrain-type :mountains}
     {:q 6 :r 6 :terrain-type :plains}
     {:q 7 :r 6 :terrain-type :plains}
     {:q 8 :r 6 :terrain-type :woods}
     {:q 9 :r 6 :terrain-type :plains}
     {:q 10 :r 6 :terrain-type :mountains}
     {:q 11 :r 6 :terrain-type :plains}
     {:q 12 :r 6 :terrain-type :plains}
     {:q 13 :r 6 :terrain-type :mountains}
     {:q 14 :r 6 :terrain-type :plains}
     ;; Row 8
     {:q 1 :r 7 :terrain-type :woods}
     {:q 2 :r 7 :terrain-type :woods}
     {:q 3 :r 7 :terrain-type :plains}
     {:q 4 :r 7 :terrain-type :plains}
     {:q 5 :r 7 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 6 :r 7 :terrain-type :mountains}
     {:q 7 :r 7 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 8 :r 7 :terrain-type :mountains}
     {:q 9 :r 7 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 10 :r 7 :terrain-type :plains}
     {:q 11 :r 7 :terrain-type :plains}
     {:q 12 :r 7 :terrain-type :mountains}
     {:q 13 :r 7 :terrain-type :plains}
     {:q 14 :r 7 :terrain-type :woods}
     ;; Row 9
     {:q 1 :r 8 :terrain-type :woods}
     {:q 2 :r 8 :terrain-type :woods}
     {:q 3 :r 8 :terrain-type :plains}
     {:q 4 :r 8 :terrain-type :mountains}
     {:q 5 :r 8 :terrain-type :plains}
     {:q 6 :r 8 :terrain-type :plains}
     {:q 7 :r 8 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 8 :r 8 :terrain-type :plains}
     {:q 9 :r 8 :terrain-type :plains}
     {:q 10 :r 8 :terrain-type :mountains}
     {:q 11 :r 8 :terrain-type :plains}
     {:q 12 :r 8 :terrain-type :plains}
     {:q 13 :r 8 :terrain-type :plains}
     {:q 14 :r 8 :terrain-type :woods}
     ;; Row 10
     {:q 0 :r 9 :terrain-type :woods}
     {:q 1 :r 9 :terrain-type :plains}
     {:q 2 :r 9 :terrain-type :plains}
     {:q 3 :r 9 :terrain-type :desert}
     {:q 4 :r 9 :terrain-type :woods} ;; Multiplayer base location (unowned)
     {:q 5 :r 9 :terrain-type :plains}
     {:q 6 :r 9 :terrain-type :plains}
     {:q 7 :r 9 :terrain-type :mountains}
     {:q 8 :r 9 :terrain-type :plains}
     {:q 9 :r 9 :terrain-type :desert}
     {:q 10 :r 9 :terrain-type :woods}
     {:q 11 :r 9 :terrain-type :woods}
     {:q 12 :r 9 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 13 :r 9 :terrain-type :plains}
     {:q 14 :r 9 :terrain-type :woods}
     ;; Row 11
     {:q 0 :r 10 :terrain-type :woods}
     {:q 1 :r 10 :terrain-type :plains}
     {:q 2 :r 10 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 3 :r 10 :terrain-type :desert}
     {:q 4 :r 10 :terrain-type :desert}
     {:q 5 :r 10 :terrain-type :plains}
     {:q 6 :r 10 :terrain-type :plains}
     {:q 7 :r 10 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 8 :r 10 :terrain-type :woods}
     {:q 9 :r 10 :terrain-type :plains}
     {:q 10 :r 10 :terrain-type :desert}
     {:q 11 :r 10 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 12 :r 10 :terrain-type :desert}
     {:q 13 :r 10 :terrain-type :plains}
     {:q 14 :r 10 :terrain-type :plains}
     ;; Row 12
     {:q 0 :r 11 :terrain-type :plains}
     {:q 1 :r 11 :terrain-type :plains}
     {:q 2 :r 11 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 3 :r 11 :terrain-type :plains} ;; Multiplayer base location (yellow)
     {:q 4 :r 11 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 5 :r 11 :terrain-type :plains}
     {:q 6 :r 11 :terrain-type :mountains}
     {:q 7 :r 11 :terrain-type :mountains}
     {:q 8 :r 11 :terrain-type :woods}
     {:q 9 :r 11 :terrain-type :plains} ;; Multiplayer base location (unowned)
     {:q 10 :r 11 :terrain-type :mountains}
     {:q 11 :r 11 :terrain-type :plains} ;; Multiplayer base location (pink)
     {:q 12 :r 11 :terrain-type :plains}
     {:q 13 :r 11 :terrain-type :plains}
     {:q 14 :r 11 :terrain-type :woods}
     ;; Row 13
     {:q 0 :r 12 :terrain-type :woods}
     {:q 1 :r 12 :terrain-type :woods}
     {:q 2 :r 12 :terrain-type :woods}
     {:q 3 :r 12 :terrain-type :mountains}
     {:q 4 :r 12 :terrain-type :desert}
     {:q 5 :r 12 :terrain-type :mountains}
     {:q 6 :r 12 :terrain-type :plains}
     {:q 7 :r 12 :terrain-type :plains}
     {:q 8 :r 12 :terrain-type :plains}
     {:q 9 :r 12 :terrain-type :plains}
     {:q 10 :r 12 :terrain-type :plains}
     {:q 11 :r 12 :terrain-type :desert}
     {:q 12 :r 12 :terrain-type :plains}
     {:q 13 :r 12 :terrain-type :woods}
     {:q 14 :r 12 :terrain-type :woods}
     ;; Row 14
     {:q 1 :r 13 :terrain-type :woods}
     {:q 2 :r 13 :terrain-type :plains}
     {:q 3 :r 13 :terrain-type :plains} ;; Multiplayer base location (yellow)
     {:q 4 :r 13 :terrain-type :plains}
     {:q 5 :r 13 :terrain-type :woods}
     {:q 6 :r 13 :terrain-type :woods}
     {:q 7 :r 13 :terrain-type :woods}
     {:q 8 :r 13 :terrain-type :woods}
     {:q 9 :r 13 :terrain-type :plains}
     {:q 10 :r 13 :terrain-type :plains} ;; Multiplayer base location (pink)
     {:q 11 :r 13 :terrain-type :plains}
     {:q 12 :r 13 :terrain-type :woods}
     ;; Row 15
     {:q 2 :r 14 :terrain-type :woods}
     {:q 3 :r 14 :terrain-type :plains}
     {:q 4 :r 14 :terrain-type :plains}
     {:q 5 :r 14 :terrain-type :woods}
     {:q 6 :r 14 :terrain-type :woods}
     {:q 8 :r 14 :terrain-type :woods}
     {:q 9 :r 14 :terrain-type :woods}
     {:q 10 :r 14 :terrain-type :plains}
     {:q 11 :r 14 :terrain-type :plains}
     {:q 12 :r 14 :terrain-type :woods}]}

   :duel
   {:id :duel
    :description "Duel"
    :created-by "Chris Vincent"
    :notes "There's only one way to settle this."
    :terrains
    [{:q 0, :r 0, :terrain-type :deep-water}
     {:q 1, :r 0, :terrain-type :deep-water}
     {:q 2, :r 0, :terrain-type :deep-water}
     {:q 3, :r 0, :terrain-type :deep-water}
     {:q 4, :r 0, :terrain-type :deep-water}
     {:q 5, :r 0, :terrain-type :deep-water}
     {:q 6, :r 0, :terrain-type :deep-water}
     {:q 7, :r 0, :terrain-type :deep-water}
     {:q 8, :r 0, :terrain-type :deep-water}
     {:q 9, :r 0, :terrain-type :deep-water}
     {:q 10, :r 0, :terrain-type :deep-water}
     {:q 11, :r 0, :terrain-type :deep-water}
     {:q 12, :r 0, :terrain-type :deep-water}
     {:q 13, :r 0, :terrain-type :deep-water}
     {:q 14, :r 0, :terrain-type :deep-water}
     {:q 15, :r 0, :terrain-type :deep-water}
     {:q 16, :r 0, :terrain-type :deep-water}
     {:q 17, :r 0, :terrain-type :deep-water}
     {:q 18, :r 0, :terrain-type :deep-water}
     {:q 19, :r 0, :terrain-type :deep-water}
     {:q 20, :r 0, :terrain-type :deep-water}
     {:q 21, :r 0, :terrain-type :deep-water}
     {:q 22, :r 0, :terrain-type :deep-water}
     {:q 23, :r 0, :terrain-type :deep-water}
     {:q 0, :r 1, :terrain-type :deep-water}
     {:q 1, :r 1, :terrain-type :deep-water}
     {:q 2, :r 1, :terrain-type :deep-water}
     {:q 3, :r 1, :terrain-type :deep-water}
     {:q 4, :r 1, :terrain-type :deep-water}
     {:q 5, :r 1, :terrain-type :deep-water}
     {:q 6, :r 1, :terrain-type :deep-water}
     {:q 7, :r 1, :terrain-type :deep-water}
     {:q 8, :r 1, :terrain-type :deep-water}
     {:q 9, :r 1, :terrain-type :deep-water}
     {:q 10, :r 1, :terrain-type :deep-water}
     {:q 11, :r 1, :terrain-type :deep-water}
     {:q 12, :r 1, :terrain-type :deep-water}
     {:q 13, :r 1, :terrain-type :deep-water}
     {:q 14, :r 1, :terrain-type :deep-water}
     {:q 15, :r 1, :terrain-type :deep-water}
     {:q 16, :r 1, :terrain-type :deep-water}
     {:q 17, :r 1, :terrain-type :deep-water}
     {:q 18, :r 1, :terrain-type :deep-water}
     {:q 19, :r 1, :terrain-type :deep-water}
     {:q 20, :r 1, :terrain-type :deep-water}
     {:q 21, :r 1, :terrain-type :deep-water}
     {:q 22, :r 1, :terrain-type :deep-water}
     {:q 0, :r 2, :terrain-type :deep-water}
     {:q 1, :r 2, :terrain-type :deep-water}
     {:q 2, :r 2, :terrain-type :deep-water}
     {:q 3, :r 2, :terrain-type :deep-water}
     {:q 4, :r 2, :terrain-type :deep-water}
     {:q 5, :r 2, :terrain-type :deep-water}
     {:q 6, :r 2, :terrain-type :deep-water}
     {:q 7, :r 2, :terrain-type :deep-water}
     {:q 8, :r 2, :terrain-type :deep-water}
     {:q 9, :r 2, :terrain-type :deep-water}
     {:q 10, :r 2, :terrain-type :deep-water}
     {:q 11, :r 2, :terrain-type :deep-water}
     {:q 12, :r 2, :terrain-type :shallow-water}
     {:q 13, :r 2, :terrain-type :shallow-water}
     {:q 14, :r 2, :terrain-type :shallow-water}
     {:q 15, :r 2, :terrain-type :shallow-water}
     {:q 16, :r 2, :terrain-type :shallow-water}
     {:q 17, :r 2, :terrain-type :shallow-water}
     {:q 18, :r 2, :terrain-type :shallow-water}
     {:q 19, :r 2, :terrain-type :deep-water}
     {:q 20, :r 2, :terrain-type :deep-water}
     {:q 21, :r 2, :terrain-type :deep-water}
     {:q 22, :r 2, :terrain-type :deep-water}
     {:q 23, :r 2, :terrain-type :deep-water}
     {:q 0, :r 3, :terrain-type :deep-water}
     {:q 1, :r 3, :terrain-type :deep-water}
     {:q 2, :r 3, :terrain-type :deep-water}
     {:q 3, :r 3, :terrain-type :deep-water}
     {:q 4, :r 3, :terrain-type :deep-water}
     {:q 5, :r 3, :terrain-type :deep-water}
     {:q 6, :r 3, :terrain-type :deep-water}
     {:q 7, :r 3, :terrain-type :deep-water}
     {:q 8, :r 3, :terrain-type :deep-water}
     {:q 9, :r 3, :terrain-type :deep-water}
     {:q 10, :r 3, :terrain-type :shallow-water}
     {:q 11, :r 3, :terrain-type :shallow-water}
     {:q 12, :r 3, :terrain-type :plains}
     {:q 13, :r 3, :terrain-type :plains}
     {:q 14, :r 3, :terrain-type :plains}
     {:q 15, :r 3, :terrain-type :plains}
     {:q 16, :r 3, :terrain-type :ford}
     {:q 17, :r 3, :terrain-type :shallow-water}
     {:q 18, :r 3, :terrain-type :shallow-water}
     {:q 19, :r 3, :terrain-type :deep-water}
     {:q 20, :r 3, :terrain-type :deep-water}
     {:q 21, :r 3, :terrain-type :deep-water}
     {:q 22, :r 3, :terrain-type :deep-water}
     {:q 0, :r 4, :terrain-type :deep-water}
     {:q 1, :r 4, :terrain-type :deep-water}
     {:q 2, :r 4, :terrain-type :deep-water}
     {:q 3, :r 4, :terrain-type :deep-water}
     {:q 4, :r 4, :terrain-type :deep-water}
     {:q 5, :r 4, :terrain-type :shallow-water}
     {:q 6, :r 4, :terrain-type :shallow-water}
     {:q 7, :r 4, :terrain-type :shallow-water}
     {:q 8, :r 4, :terrain-type :shallow-water}
     {:q 9, :r 4, :terrain-type :shallow-water}
     {:q 10, :r 4, :terrain-type :shallow-water}
     {:q 11, :r 4, :terrain-type :woods}
     {:q 12, :r 4, :terrain-type :plains}
     {:q 13, :r 4, :terrain-type :plains}
     {:q 14, :r 4, :terrain-type :plains}
     {:q 15, :r 4, :terrain-type :plains}
     {:q 16, :r 4, :terrain-type :plains}
     {:q 17, :r 4, :terrain-type :ford}
     {:q 18, :r 4, :terrain-type :swamp}
     {:q 19, :r 4, :terrain-type :shallow-water}
     {:q 20, :r 4, :terrain-type :deep-water}
     {:q 21, :r 4, :terrain-type :deep-water}
     {:q 22, :r 4, :terrain-type :deep-water}
     {:q 23, :r 4, :terrain-type :deep-water}
     {:q 0, :r 5, :terrain-type :deep-water}
     {:q 1, :r 5, :terrain-type :deep-water}
     {:q 2, :r 5, :terrain-type :deep-water}
     {:q 3, :r 5, :terrain-type :deep-water}
     {:q 4, :r 5, :terrain-type :shallow-water}
     {:q 5, :r 5, :terrain-type :plains}
     {:q 6, :r 5, :terrain-type :plains}
     {:q 7, :r 5, :terrain-type :plains}
     {:q 8, :r 5, :terrain-type :woods}
     {:q 9, :r 5, :terrain-type :woods}
     {:q 10, :r 5, :terrain-type :mountains}
     {:q 11, :r 5, :terrain-type :woods}
     {:q 12, :r 5, :terrain-type :plains}
     {:q 13, :r 5, :terrain-type :plains}
     {:q 14, :r 5, :terrain-type :plains}
     {:q 15, :r 5, :terrain-type :plains}
     {:q 16, :r 5, :terrain-type :swamp}
     {:q 17, :r 5, :terrain-type :ford}
     {:q 18, :r 5, :terrain-type :woods}
     {:q 19, :r 5, :terrain-type :shallow-water}
     {:q 20, :r 5, :terrain-type :deep-water}
     {:q 21, :r 5, :terrain-type :deep-water}
     {:q 22, :r 5, :terrain-type :deep-water}
     {:q 0, :r 6, :terrain-type :deep-water}
     {:q 1, :r 6, :terrain-type :deep-water}
     {:q 2, :r 6, :terrain-type :deep-water}
     {:q 3, :r 6, :terrain-type :deep-water}
     {:q 4, :r 6, :terrain-type :shallow-water}
     {:q 5, :r 6, :terrain-type :plains}
     {:q 6, :r 6, :terrain-type :plains}
     {:q 7, :r 6, :terrain-type :plains}
     {:q 8, :r 6, :terrain-type :woods}
     {:q 9, :r 6, :terrain-type :woods}
     {:q 10, :r 6, :terrain-type :plains}
     {:q 11, :r 6, :terrain-type :plains}
     {:q 12, :r 6, :terrain-type :shallow-water}
     {:q 13, :r 6, :terrain-type :shallow-water}
     {:q 14, :r 6, :terrain-type :plains}
     {:q 15, :r 6, :terrain-type :plains}
     {:q 16, :r 6, :terrain-type :woods}
     {:q 17, :r 6, :terrain-type :swamp}
     {:q 18, :r 6, :terrain-type :swamp}
     {:q 19, :r 6, :terrain-type :woods}
     {:q 20, :r 6, :terrain-type :shallow-water}
     {:q 21, :r 6, :terrain-type :deep-water}
     {:q 22, :r 6, :terrain-type :deep-water}
     {:q 23, :r 6, :terrain-type :deep-water}
     {:q 0, :r 7, :terrain-type :deep-water}
     {:q 1, :r 7, :terrain-type :deep-water}
     {:q 2, :r 7, :terrain-type :deep-water}
     {:q 3, :r 7, :terrain-type :deep-water}
     {:q 4, :r 7, :terrain-type :deep-water}
     {:q 5, :r 7, :terrain-type :plains}
     {:q 6, :r 7, :terrain-type :plains}
     {:q 7, :r 7, :terrain-type :woods}
     {:q 8, :r 7, :terrain-type :mountains}
     {:q 9, :r 7, :terrain-type :plains}
     {:q 10, :r 7, :terrain-type :plains}
     {:q 11, :r 7, :terrain-type :shallow-water}
     {:q 12, :r 7, :terrain-type :swamp}
     {:q 13, :r 7, :terrain-type :plains}
     {:q 14, :r 7, :terrain-type :shallow-water}
     {:q 15, :r 7, :terrain-type :woods}
     {:q 16, :r 7, :terrain-type :woods}
     {:q 17, :r 7, :terrain-type :woods}
     {:q 18, :r 7, :terrain-type :swamp}
     {:q 19, :r 7, :terrain-type :woods}
     {:q 20, :r 7, :terrain-type :shallow-water}
     {:q 21, :r 7, :terrain-type :deep-water}
     {:q 22, :r 7, :terrain-type :deep-water}
     {:q 0, :r 8, :terrain-type :deep-water}
     {:q 1, :r 8, :terrain-type :deep-water}
     {:q 2, :r 8, :terrain-type :deep-water}
     {:q 3, :r 8, :terrain-type :deep-water}
     {:q 4, :r 8, :terrain-type :deep-water}
     {:q 5, :r 8, :terrain-type :mountains}
     {:q 6, :r 8, :terrain-type :mountains}
     {:q 7, :r 8, :terrain-type :mountains}
     {:q 8, :r 8, :terrain-type :plains}
     {:q 9, :r 8, :terrain-type :plains}
     {:q 10, :r 8, :terrain-type :plains}
     {:q 11, :r 8, :terrain-type :plains}
     {:q 12, :r 8, :terrain-type :shallow-water}
     {:q 13, :r 8, :terrain-type :shallow-water}
     {:q 14, :r 8, :terrain-type :swamp}
     {:q 15, :r 8, :terrain-type :shallow-water}
     {:q 16, :r 8, :terrain-type :woods}
     {:q 17, :r 8, :terrain-type :woods}
     {:q 18, :r 8, :terrain-type :woods}
     {:q 19, :r 8, :terrain-type :swamp}
     {:q 20, :r 8, :terrain-type :woods}
     {:q 21, :r 8, :terrain-type :shallow-water}
     {:q 22, :r 8, :terrain-type :deep-water}
     {:q 23, :r 8, :terrain-type :deep-water}
     {:q 0, :r 9, :terrain-type :deep-water}
     {:q 1, :r 9, :terrain-type :deep-water}
     {:q 2, :r 9, :terrain-type :deep-water}
     {:q 3, :r 9, :terrain-type :deep-water}
     {:q 4, :r 9, :terrain-type :mountains}
     {:q 5, :r 9, :terrain-type :mountains}
     {:q 6, :r 9, :terrain-type :plains}
     {:q 7, :r 9, :terrain-type :plains}
     {:q 8, :r 9, :terrain-type :plains}
     {:q 9, :r 9, :terrain-type :woods}
     {:q 10, :r 9, :terrain-type :plains}
     {:q 11, :r 9, :terrain-type :plains}
     {:q 12, :r 9, :terrain-type :shallow-water}
     {:q 13, :r 9, :terrain-type :shallow-water}
     {:q 14, :r 9, :terrain-type :shallow-water}
     {:q 15, :r 9, :terrain-type :mountains}
     {:q 16, :r 9, :terrain-type :mountains}
     {:q 17, :r 9, :terrain-type :mountains}
     {:q 18, :r 9, :terrain-type :swamp}
     {:q 19, :r 9, :terrain-type :woods}
     {:q 20, :r 9, :terrain-type :shallow-water}
     {:q 21, :r 9, :terrain-type :deep-water}
     {:q 22, :r 9, :terrain-type :deep-water}
     {:q 0, :r 10, :terrain-type :deep-water}
     {:q 1, :r 10, :terrain-type :deep-water}
     {:q 2, :r 10, :terrain-type :deep-water}
     {:q 3, :r 10, :terrain-type :deep-water}
     {:q 4, :r 10, :terrain-type :shallow-water}
     {:q 5, :r 10, :terrain-type :mountains}
     {:q 6, :r 10, :terrain-type :plains}
     {:q 7, :r 10, :terrain-type :plains}
     {:q 8, :r 10, :terrain-type :plains}
     {:q 9, :r 10, :terrain-type :woods}
     {:q 10, :r 10, :terrain-type :woods}
     {:q 11, :r 10, :terrain-type :plains}
     {:q 12, :r 10, :terrain-type :plains}
     {:q 13, :r 10, :terrain-type :plains}
     {:q 14, :r 10, :terrain-type :mountains}
     {:q 15, :r 10, :terrain-type :mountains}
     {:q 16, :r 10, :terrain-type :mountains}
     {:q 17, :r 10, :terrain-type :mountains}
     {:q 18, :r 10, :terrain-type :swamp}
     {:q 19, :r 10, :terrain-type :woods}
     {:q 20, :r 10, :terrain-type :shallow-water}
     {:q 21, :r 10, :terrain-type :deep-water}
     {:q 22, :r 10, :terrain-type :deep-water}
     {:q 23, :r 10, :terrain-type :deep-water}
     {:q 0, :r 11, :terrain-type :deep-water}
     {:q 1, :r 11, :terrain-type :deep-water}
     {:q 2, :r 11, :terrain-type :deep-water}
     {:q 3, :r 11, :terrain-type :shallow-water}
     {:q 4, :r 11, :terrain-type :woods}
     {:q 5, :r 11, :terrain-type :plains}
     {:q 6, :r 11, :terrain-type :plains}
     {:q 7, :r 11, :terrain-type :plains}
     {:q 8, :r 11, :terrain-type :plains}
     {:q 9, :r 11, :terrain-type :woods}
     {:q 10, :r 11, :terrain-type :plains}
     {:q 11, :r 11, :terrain-type :plains}
     {:q 12, :r 11, :terrain-type :plains}
     {:q 13, :r 11, :terrain-type :woods}
     {:q 14, :r 11, :terrain-type :plains}
     {:q 15, :r 11, :terrain-type :plains}
     {:q 16, :r 11, :terrain-type :plains}
     {:q 17, :r 11, :terrain-type :plains}
     {:q 18, :r 11, :terrain-type :woods}
     {:q 19, :r 11, :terrain-type :shallow-water}
     {:q 20, :r 11, :terrain-type :deep-water}
     {:q 21, :r 11, :terrain-type :deep-water}
     {:q 22, :r 11, :terrain-type :deep-water}
     {:q 0, :r 12, :terrain-type :deep-water}
     {:q 1, :r 12, :terrain-type :deep-water}
     {:q 2, :r 12, :terrain-type :deep-water}
     {:q 3, :r 12, :terrain-type :shallow-water}
     {:q 4, :r 12, :terrain-type :woods}
     {:q 5, :r 12, :terrain-type :swamp}
     {:q 6, :r 12, :terrain-type :mountains}
     {:q 7, :r 12, :terrain-type :mountains}
     {:q 8, :r 12, :terrain-type :mountains}
     {:q 9, :r 12, :terrain-type :mountains}
     {:q 10, :r 12, :terrain-type :plains}
     {:q 11, :r 12, :terrain-type :plains}
     {:q 12, :r 12, :terrain-type :plains}
     {:q 13, :r 12, :terrain-type :woods}
     {:q 14, :r 12, :terrain-type :plains}
     {:q 15, :r 12, :terrain-type :plains}
     {:q 16, :r 12, :terrain-type :plains}
     {:q 17, :r 12, :terrain-type :plains}
     {:q 18, :r 12, :terrain-type :mountains}
     {:q 19, :r 12, :terrain-type :shallow-water}
     {:q 20, :r 12, :terrain-type :deep-water}
     {:q 21, :r 12, :terrain-type :deep-water}
     {:q 22, :r 12, :terrain-type :deep-water}
     {:q 23, :r 12, :terrain-type :deep-water}
     {:q 0, :r 13, :terrain-type :deep-water}
     {:q 1, :r 13, :terrain-type :deep-water}
     {:q 2, :r 13, :terrain-type :shallow-water}
     {:q 3, :r 13, :terrain-type :woods}
     {:q 4, :r 13, :terrain-type :swamp}
     {:q 5, :r 13, :terrain-type :mountains}
     {:q 6, :r 13, :terrain-type :mountains}
     {:q 7, :r 13, :terrain-type :mountains}
     {:q 8, :r 13, :terrain-type :shallow-water}
     {:q 9, :r 13, :terrain-type :shallow-water}
     {:q 10, :r 13, :terrain-type :shallow-water}
     {:q 11, :r 13, :terrain-type :plains}
     {:q 12, :r 13, :terrain-type :plains}
     {:q 13, :r 13, :terrain-type :woods}
     {:q 14, :r 13, :terrain-type :plains}
     {:q 15, :r 13, :terrain-type :plains}
     {:q 16, :r 13, :terrain-type :plains}
     {:q 17, :r 13, :terrain-type :mountains}
     {:q 18, :r 13, :terrain-type :mountains}
     {:q 19, :r 13, :terrain-type :deep-water}
     {:q 20, :r 13, :terrain-type :deep-water}
     {:q 21, :r 13, :terrain-type :deep-water}
     {:q 22, :r 13, :terrain-type :deep-water}
     {:q 0, :r 14, :terrain-type :deep-water}
     {:q 1, :r 14, :terrain-type :deep-water}
     {:q 2, :r 14, :terrain-type :shallow-water}
     {:q 3, :r 14, :terrain-type :woods}
     {:q 4, :r 14, :terrain-type :swamp}
     {:q 5, :r 14, :terrain-type :woods}
     {:q 6, :r 14, :terrain-type :woods}
     {:q 7, :r 14, :terrain-type :woods}
     {:q 8, :r 14, :terrain-type :shallow-water}
     {:q 9, :r 14, :terrain-type :swamp}
     {:q 10, :r 14, :terrain-type :shallow-water}
     {:q 11, :r 14, :terrain-type :shallow-water}
     {:q 12, :r 14, :terrain-type :plains}
     {:q 13, :r 14, :terrain-type :plains}
     {:q 14, :r 14, :terrain-type :plains}
     {:q 15, :r 14, :terrain-type :plains}
     {:q 16, :r 14, :terrain-type :mountains}
     {:q 17, :r 14, :terrain-type :mountains}
     {:q 18, :r 14, :terrain-type :mountains}
     {:q 19, :r 14, :terrain-type :deep-water}
     {:q 20, :r 14, :terrain-type :deep-water}
     {:q 21, :r 14, :terrain-type :deep-water}
     {:q 22, :r 14, :terrain-type :deep-water}
     {:q 23, :r 14, :terrain-type :deep-water}
     {:q 0, :r 15, :terrain-type :deep-water}
     {:q 1, :r 15, :terrain-type :deep-water}
     {:q 2, :r 15, :terrain-type :shallow-water}
     {:q 3, :r 15, :terrain-type :woods}
     {:q 4, :r 15, :terrain-type :swamp}
     {:q 5, :r 15, :terrain-type :woods}
     {:q 6, :r 15, :terrain-type :woods}
     {:q 7, :r 15, :terrain-type :woods}
     {:q 8, :r 15, :terrain-type :shallow-water}
     {:q 9, :r 15, :terrain-type :plains}
     {:q 10, :r 15, :terrain-type :swamp}
     {:q 11, :r 15, :terrain-type :shallow-water}
     {:q 12, :r 15, :terrain-type :plains}
     {:q 13, :r 15, :terrain-type :plains}
     {:q 14, :r 15, :terrain-type :mountains}
     {:q 15, :r 15, :terrain-type :woods}
     {:q 16, :r 15, :terrain-type :plains}
     {:q 17, :r 15, :terrain-type :plains}
     {:q 18, :r 15, :terrain-type :plains}
     {:q 19, :r 15, :terrain-type :deep-water}
     {:q 20, :r 15, :terrain-type :deep-water}
     {:q 21, :r 15, :terrain-type :deep-water}
     {:q 22, :r 15, :terrain-type :deep-water}
     {:q 0, :r 16, :terrain-type :deep-water}
     {:q 1, :r 16, :terrain-type :deep-water}
     {:q 2, :r 16, :terrain-type :deep-water}
     {:q 3, :r 16, :terrain-type :shallow-water}
     {:q 4, :r 16, :terrain-type :woods}
     {:q 5, :r 16, :terrain-type :swamp}
     {:q 6, :r 16, :terrain-type :swamp}
     {:q 7, :r 16, :terrain-type :woods}
     {:q 8, :r 16, :terrain-type :plains}
     {:q 9, :r 16, :terrain-type :plains}
     {:q 10, :r 16, :terrain-type :shallow-water}
     {:q 11, :r 16, :terrain-type :shallow-water}
     {:q 12, :r 16, :terrain-type :plains}
     {:q 13, :r 16, :terrain-type :plains}
     {:q 14, :r 16, :terrain-type :woods}
     {:q 15, :r 16, :terrain-type :woods}
     {:q 16, :r 16, :terrain-type :plains}
     {:q 17, :r 16, :terrain-type :plains}
     {:q 18, :r 16, :terrain-type :plains}
     {:q 19, :r 16, :terrain-type :shallow-water}
     {:q 20, :r 16, :terrain-type :deep-water}
     {:q 21, :r 16, :terrain-type :deep-water}
     {:q 22, :r 16, :terrain-type :deep-water}
     {:q 23, :r 16, :terrain-type :deep-water}
     {:q 0, :r 17, :terrain-type :deep-water}
     {:q 1, :r 17, :terrain-type :deep-water}
     {:q 2, :r 17, :terrain-type :deep-water}
     {:q 3, :r 17, :terrain-type :shallow-water}
     {:q 4, :r 17, :terrain-type :woods}
     {:q 5, :r 17, :terrain-type :ford}
     {:q 6, :r 17, :terrain-type :swamp}
     {:q 7, :r 17, :terrain-type :plains}
     {:q 8, :r 17, :terrain-type :plains}
     {:q 9, :r 17, :terrain-type :plains}
     {:q 10, :r 17, :terrain-type :plains}
     {:q 11, :r 17, :terrain-type :woods}
     {:q 12, :r 17, :terrain-type :mountains}
     {:q 13, :r 17, :terrain-type :woods}
     {:q 14, :r 17, :terrain-type :woods}
     {:q 15, :r 17, :terrain-type :plains}
     {:q 16, :r 17, :terrain-type :plains}
     {:q 17, :r 17, :terrain-type :plains}
     {:q 18, :r 17, :terrain-type :shallow-water}
     {:q 19, :r 17, :terrain-type :deep-water}
     {:q 20, :r 17, :terrain-type :deep-water}
     {:q 21, :r 17, :terrain-type :deep-water}
     {:q 22, :r 17, :terrain-type :deep-water}
     {:q 0, :r 18, :terrain-type :deep-water}
     {:q 1, :r 18, :terrain-type :deep-water}
     {:q 2, :r 18, :terrain-type :deep-water}
     {:q 3, :r 18, :terrain-type :deep-water}
     {:q 4, :r 18, :terrain-type :shallow-water}
     {:q 5, :r 18, :terrain-type :swamp}
     {:q 6, :r 18, :terrain-type :ford}
     {:q 7, :r 18, :terrain-type :plains}
     {:q 8, :r 18, :terrain-type :plains}
     {:q 9, :r 18, :terrain-type :plains}
     {:q 10, :r 18, :terrain-type :plains}
     {:q 11, :r 18, :terrain-type :plains}
     {:q 12, :r 18, :terrain-type :woods}
     {:q 13, :r 18, :terrain-type :shallow-water}
     {:q 14, :r 18, :terrain-type :shallow-water}
     {:q 15, :r 18, :terrain-type :shallow-water}
     {:q 16, :r 18, :terrain-type :shallow-water}
     {:q 17, :r 18, :terrain-type :shallow-water}
     {:q 18, :r 18, :terrain-type :shallow-water}
     {:q 19, :r 18, :terrain-type :deep-water}
     {:q 20, :r 18, :terrain-type :deep-water}
     {:q 21, :r 18, :terrain-type :deep-water}
     {:q 22, :r 18, :terrain-type :deep-water}
     {:q 23, :r 18, :terrain-type :deep-water}
     {:q 0, :r 19, :terrain-type :deep-water}
     {:q 1, :r 19, :terrain-type :deep-water}
     {:q 2, :r 19, :terrain-type :deep-water}
     {:q 3, :r 19, :terrain-type :deep-water}
     {:q 4, :r 19, :terrain-type :shallow-water}
     {:q 5, :r 19, :terrain-type :shallow-water}
     {:q 6, :r 19, :terrain-type :ford}
     {:q 7, :r 19, :terrain-type :plains}
     {:q 8, :r 19, :terrain-type :plains}
     {:q 9, :r 19, :terrain-type :plains}
     {:q 10, :r 19, :terrain-type :plains}
     {:q 11, :r 19, :terrain-type :shallow-water}
     {:q 12, :r 19, :terrain-type :shallow-water}
     {:q 13, :r 19, :terrain-type :deep-water}
     {:q 14, :r 19, :terrain-type :deep-water}
     {:q 15, :r 19, :terrain-type :deep-water}
     {:q 16, :r 19, :terrain-type :deep-water}
     {:q 17, :r 19, :terrain-type :deep-water}
     {:q 18, :r 19, :terrain-type :deep-water}
     {:q 19, :r 19, :terrain-type :deep-water}
     {:q 20, :r 19, :terrain-type :deep-water}
     {:q 21, :r 19, :terrain-type :deep-water}
     {:q 22, :r 19, :terrain-type :deep-water}
     {:q 0, :r 20, :terrain-type :deep-water}
     {:q 1, :r 20, :terrain-type :deep-water}
     {:q 2, :r 20, :terrain-type :deep-water}
     {:q 3, :r 20, :terrain-type :deep-water}
     {:q 4, :r 20, :terrain-type :deep-water}
     {:q 5, :r 20, :terrain-type :shallow-water}
     {:q 6, :r 20, :terrain-type :shallow-water}
     {:q 7, :r 20, :terrain-type :shallow-water}
     {:q 8, :r 20, :terrain-type :shallow-water}
     {:q 9, :r 20, :terrain-type :shallow-water}
     {:q 10, :r 20, :terrain-type :shallow-water}
     {:q 11, :r 20, :terrain-type :shallow-water}
     {:q 12, :r 20, :terrain-type :deep-water}
     {:q 13, :r 20, :terrain-type :deep-water}
     {:q 14, :r 20, :terrain-type :deep-water}
     {:q 15, :r 20, :terrain-type :deep-water}
     {:q 16, :r 20, :terrain-type :deep-water}
     {:q 17, :r 20, :terrain-type :deep-water}
     {:q 18, :r 20, :terrain-type :deep-water}
     {:q 19, :r 20, :terrain-type :deep-water}
     {:q 20, :r 20, :terrain-type :deep-water}
     {:q 21, :r 20, :terrain-type :deep-water}
     {:q 22, :r 20, :terrain-type :deep-water}
     {:q 23, :r 20, :terrain-type :deep-water}
     {:q 0, :r 21, :terrain-type :deep-water}
     {:q 1, :r 21, :terrain-type :deep-water}
     {:q 2, :r 21, :terrain-type :deep-water}
     {:q 3, :r 21, :terrain-type :deep-water}
     {:q 4, :r 21, :terrain-type :deep-water}
     {:q 5, :r 21, :terrain-type :deep-water}
     {:q 6, :r 21, :terrain-type :deep-water}
     {:q 7, :r 21, :terrain-type :deep-water}
     {:q 8, :r 21, :terrain-type :deep-water}
     {:q 9, :r 21, :terrain-type :deep-water}
     {:q 10, :r 21, :terrain-type :deep-water}
     {:q 11, :r 21, :terrain-type :deep-water}
     {:q 12, :r 21, :terrain-type :deep-water}
     {:q 13, :r 21, :terrain-type :deep-water}
     {:q 14, :r 21, :terrain-type :deep-water}
     {:q 15, :r 21, :terrain-type :deep-water}
     {:q 16, :r 21, :terrain-type :deep-water}
     {:q 17, :r 21, :terrain-type :deep-water}
     {:q 18, :r 21, :terrain-type :deep-water}
     {:q 19, :r 21, :terrain-type :deep-water}
     {:q 20, :r 21, :terrain-type :deep-water}
     {:q 21, :r 21, :terrain-type :deep-water}
     {:q 22, :r 21, :terrain-type :deep-water}
     {:q 0, :r 22, :terrain-type :deep-water}
     {:q 1, :r 22, :terrain-type :deep-water}
     {:q 2, :r 22, :terrain-type :deep-water}
     {:q 3, :r 22, :terrain-type :deep-water}
     {:q 4, :r 22, :terrain-type :deep-water}
     {:q 5, :r 22, :terrain-type :deep-water}
     {:q 6, :r 22, :terrain-type :deep-water}
     {:q 7, :r 22, :terrain-type :deep-water}
     {:q 8, :r 22, :terrain-type :deep-water}
     {:q 9, :r 22, :terrain-type :deep-water}
     {:q 10, :r 22, :terrain-type :deep-water}
     {:q 11, :r 22, :terrain-type :deep-water}
     {:q 12, :r 22, :terrain-type :deep-water}
     {:q 13, :r 22, :terrain-type :deep-water}
     {:q 14, :r 22, :terrain-type :deep-water}
     {:q 15, :r 22, :terrain-type :deep-water}
     {:q 16, :r 22, :terrain-type :deep-water}
     {:q 17, :r 22, :terrain-type :deep-water}
     {:q 18, :r 22, :terrain-type :deep-water}
     {:q 19, :r 22, :terrain-type :deep-water}
     {:q 20, :r 22, :terrain-type :deep-water}
     {:q 21, :r 22, :terrain-type :deep-water}
     {:q 22, :r 22, :terrain-type :deep-water}
     {:q 23, :r 22, :terrain-type :deep-water}]}})

;; TODO: add support for :allowed-unit-types
(def scenarios
  {:sterlings-aruba-multiplayer
   {:id :sterlings-aruba-multiplayer
    :description "Sterling's Aruba Multiplayer"
    :created-by "Weewar"
    :ruleset-id :zetawar
    :map-id :sterlings-aruba
    :max-count-per-unit 10
    :credits-per-base 100
    :bases
    [{:q 1 :r 2 :base-type :base}
     {:q 1 :r 4 :base-type :seaport}
     {:q 2 :r 1 :base-type :base}
     {:q 2 :r 4 :base-type :airfield}
     {:q 5 :r 7 :base-type :base}
     {:q 5 :r 8 :base-type :seaport}
     {:q 6 :r 5 :base-type :airfield}
     {:q 7 :r 6 :base-type :base}]
    :factions
    [{:color :blue
      :credits 300
      :ai false
      :bases [{:q 1 :r 2}]
      :units [{:q 2 :r 2 :unit-type :infantry}]}
     {:color :red
      :credits 300
      :ai true
      :bases [{:q 7 :r 6}]
      :units [{:q 7 :r 7 :unit-type :infantry}
              {:q 7 :r 8 :unit-type :infantry}]}]}

   :sterlings-aruba-ai-vs-ai
   {:id :sterlings-aruba-ai-vs-ai
    :description "Sterling's Aruba AI vs AI"
    :created-by "Weewar"
    :ruleset-id :zetawar
    :map-id :sterlings-aruba
    :max-count-per-unit 10
    :credits-per-base 100
    :bases
    [{:q 1 :r 2 :base-type :base}
     {:q 1 :r 4 :base-type :seaport}
     {:q 2 :r 1 :base-type :base}
     {:q 2 :r 4 :base-type :airfield}
     {:q 5 :r 7 :base-type :base}
     {:q 5 :r 8 :base-type :seaport}
     {:q 6 :r 5 :base-type :airfield}
     {:q 7 :r 6 :base-type :base}]
    :factions
    [{:color :blue
      :credits 300
      :ai true
      :bases [{:q 1 :r 2}]
      :units [{:q 2 :r 2 :unit-type :infantry}]}
     {:color :red
      :credits 300
      :ai true
      :bases [{:q 7 :r 6}]
      :units [{:q 7 :r 7 :unit-type :infantry}
              {:q 7 :r 8 :unit-type :infantry}]}]}

   :city-sprawl-multiplayer
   {:id :city-sprawl-multiplayer
    :description "City Sprawl Multiplayer"
    :created-by "Weewar"
    :ruleset-id :zetawar
    :map-id :city-sprawl
    :max-count-per-unit 10
    :credits-per-base 50
    :bases
    [{:q 4  :r 2 :base-type :base}
     {:q 12 :r 2 :base-type :base}
     {:q 3  :r 3 :base-type :base}
     {:q 5  :r 3 :base-type :base}
     {:q 10 :r 3 :base-type :base}
     {:q 2  :r 4 :base-type :base}
     {:q 4  :r 4 :base-type :base}
     {:q 12 :r 4 :base-type :base}
     {:q 7  :r 5 :base-type :base}
     {:q 11 :r 5 :base-type :base}
     {:q 13 :r 5 :base-type :base}
     {:q 5  :r 7 :base-type :base}
     {:q 7  :r 7 :base-type :base}
     {:q 9  :r 7 :base-type :base}
     {:q 7  :r 8 :base-type :base}
     {:q 12 :r 9 :base-type :base}
     {:q 2  :r 10 :base-type :base}
     {:q 7  :r 10 :base-type :base}
     {:q 11 :r 10 :base-type :base}
     {:q 2  :r 11 :base-type :base}
     {:q 3  :r 11 :base-type :base}
     {:q 4  :r 11 :base-type :base}
     {:q 9  :r 11 :base-type :base}
     {:q 11 :r 11 :base-type :base}
     {:q 3  :r 13 :base-type :base}
     {:q 10 :r 13 :base-type :base}]
    ;; TODO: determine best faction order
    :factions
    [{:color :red
      :credits 300
      :ai false
      :bases [{:q 3  :r 3}
              {:q 2  :r 4}]
      :units [{:q 3 :r 2 :unit-type :infantry}
              {:q 2 :r 3 :unit-type :infantry}]}
     {:color :blue
      :credits 300
      :ai true
      :bases [{:q 12 :r 4}
              {:q 13 :r 5}]
      :units [{:q 12 :r 3 :unit-type :infantry}
              {:q 13 :r 4 :unit-type :infantry}]}
     {:color :yellow
      :credits 300
      :ai true
      :bases [{:q 3 :r 11}
              {:q 3 :r 13}]
      :units [{:q 2 :r 12 :unit-type :infantry}
              {:q 3 :r 12 :unit-type :infantry}]}
     {:color :pink
      :credits 300
      :ai true
      :bases [{:q 11 :r 11}
              {:q 10 :r 13}]
      :units [{:q 10 :r 11 :unit-type :infantry}
              {:q 11 :r 12 :unit-type :infantry}]}]}

   :duel-multiplayer
   {:id :duel-multiplayer
    :description "Duel Multiplayer"
    :created-by "Chris Vincent"
    :notes "There's only one way to settle this."
    :ruleset-id :zetawar
    :map-id :duel
    :max-count-per-unit 10
    :credits-per-base 100
    :bases
    [{:q 4, :r 7, :base-type :seaport}
     {:q 5, :r 6, :base-type :base}
     {:q 5, :r 19, :base-type :base}
     {:q 6, :r 5, :base-type :base}
     {:q 8, :r 18, :base-type :airfield}
     {:q 9, :r 10, :base-type :base}
     {:q 10, :r 14, :base-type :base}
     {:q 10, :r 18, :base-type :base}
     {:q 13, :r 4, :base-type :base}
     {:q 13, :r 8, :base-type :base}
     {:q 14, :r 12, :base-type :base}
     {:q 15, :r 4, :base-type :airfield}
     {:q 16, :r 17, :base-type :base}
     {:q 17, :r 3, :base-type :base}
     {:q 18, :r 15, :base-type :seaport}
     {:q 18, :r 16, :base-type :base}]
    :factions
    [{:color :red
      :credits 300
      :ai false
      :bases [{:q 4, :r 7}
              {:q 5, :r 6}
              {:q 6, :r 5}]
      :units [{:q 4, :r 8, :unit-type :cruiser}
              {:q 5, :r 5, :unit-type :infantry}]}
     {:color :blue
      :credits 300
      :ai true
      :bases [{:q 16, :r 17}
              {:q 18, :r 15}
              {:q 18, :r 16}]
      :units [{:q 17, :r 17, :unit-type :infantry}
              {:q 19, :r 14, :unit-type :cruiser}]}]}})
